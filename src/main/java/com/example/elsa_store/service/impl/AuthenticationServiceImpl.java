package com.example.elsa_store.service.impl;

import com.example.elsa_store.exception.AppException;
import com.example.elsa_store.exception.ErrorCode;
import com.example.elsa_store.mapper.UserMapper;
import com.example.elsa_store.service.AuthenticationService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.example.elsa_store.dto.request.AuthenticationRequest;
import com.example.elsa_store.dto.request.IntrospectRequest;
import com.example.elsa_store.dto.request.RefreshTokenRequest;
import com.example.elsa_store.dto.response.AuthenticationResponse;
import com.example.elsa_store.dto.response.IntrospectResponse;
import com.example.elsa_store.entity.*;
<<<<<<< HEAD
import com.example.elsa_store.entity.enums.Role;
=======
>>>>>>> upstream/develop
import com.example.elsa_store.repository.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationServiceImpl implements AuthenticationService {
    @NonFinal
    @Value("${jwt.valid-duration}")
    protected long VALID_DURATION;

    @NonFinal
    @Value("${jwt.refreshable-duration}")
    protected long REFRESHABLE_DURATION;

    static Logger log = LoggerFactory.getLogger(AuthenticationService.class);
    UserRepository userRepository;
    InvalidatedRepository invalidatedRepository;

    @NonFinal
    @Value("${jwt.signer-key}")
    protected String SIGNER_KEY;
    @Override
    public IntrospectResponse introspect(IntrospectRequest request) throws ParseException, JOSEException {
        var token = request.getToken();

        boolean isValid = true;
        try {
            verifyToken(token);
        } catch (AppException e) {
            isValid = false;
        }

        return IntrospectResponse.builder().valid(isValid).build();
    }

    @Override
    public AuthenticationResponse authenticated(AuthenticationRequest request) {
        User user = userRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.INCORRECT_ACCOUNT_OR_PASSWORD));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!authenticated) throw new AppException(ErrorCode.INCORRECT_ACCOUNT_OR_PASSWORD);

        String jit = UUID.randomUUID().toString();
        var accessToken  = generateToken(user, "ACCESS", jit);
        var refreshToken  = generateToken(user, "REFRESH", jit);

        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .authenticated(true)
                .role(user.getRole())
                .build();

        authenticationResponse.setUser(UserMapper.toResponse(user));

        return authenticationResponse;
    }

    @Override
    public AuthenticationResponse refreshToken(RefreshTokenRequest request)throws ParseException, JOSEException {
        SignedJWT signJWT = verifyToken(request.getToken());

        var claims = signJWT.getJWTClaimsSet();

        if (!"REFRESH".equals(claims.getStringClaim("token_type"))) {
            throw new AppException(ErrorCode.UN_AUTHENTICATED);
        }

        var jit = claims.getJWTID();
        var expiryTime = claims.getExpirationTime();

        invalidatedRepository.save(
                InvalidatedToken.builder().id(jit).expiryTime(expiryTime).build());

        String username = claims.getSubject();

        User user =
                userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.UN_AUTHENTICATED));

        String jitNew = UUID.randomUUID().toString();
        var accessToken  = generateToken(user, "ACCESS", jitNew);
        var refreshToken  = generateToken(user, "REFRESH", jitNew);

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .authenticated(true)
                .build();
    }

    @Override
    public void logout()throws ParseException, JOSEException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof JwtAuthenticationToken jwtAuth)) {
            throw new RuntimeException("Unauthenticated");
        }

        Jwt jwt = jwtAuth.getToken();
        String tokenValue = jwt.getTokenValue();

        SignedJWT  signedJWT  = verifyToken(tokenValue);

        String jti = signedJWT .getJWTClaimsSet().getJWTID();
        Date expiryTime = signedJWT .getJWTClaimsSet().getExpirationTime();

        InvalidatedToken invalidatedToken =
                InvalidatedToken.builder().id(jti).expiryTime(expiryTime).build();

        invalidatedRepository.save(invalidatedToken);
    }

    private SignedJWT verifyToken(String token) throws ParseException, JOSEException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        var verified = signedJWT.verify(verifier);
        if (!verified) throw new AppException(ErrorCode.UN_AUTHENTICATED);

        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();


        if (expiryTime.before(new Date())) throw new AppException(ErrorCode.UN_AUTHENTICATED);

        if (invalidatedRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID()))
            throw new AppException(ErrorCode.UN_AUTHENTICATED);
        return signedJWT;
    }

    private String generateToken(User user, String tokenType, String jit) {
        long duration = tokenType.equals("ACCESS")
                ? VALID_DURATION
                : REFRESHABLE_DURATION;
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("vanmo.com") // domain
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(duration, ChronoUnit.SECONDS).toEpochMilli())) // thời gian hết hạn
                .jwtID(jit)
                .claim("scope", buildScope(user))
                .claim("token_type", tokenType)
                .build();


        try {
            JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

            Payload payload = new Payload(jwtClaimsSet.toJSONObject());

            JWSObject jwsObject = new JWSObject(header, payload);

            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create token ", e);
            throw new RuntimeException(e);
        }
    }

    private String buildScope(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (user.getRole() != null) stringJoiner.add("ROLE_" + user.getRole());

        return stringJoiner.toString();
    }
}
