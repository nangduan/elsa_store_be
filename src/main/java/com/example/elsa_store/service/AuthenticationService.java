package com.example.elsa_store.service;

import com.example.elsa_store.dto.response.IntrospectResponse;
import com.nimbusds.jose.JOSEException;
import com.example.elsa_store.dto.request.AuthenticationRequest;
import com.example.elsa_store.dto.request.IntrospectRequest;
import com.example.elsa_store.dto.request.RefreshTokenRequest;
import com.example.elsa_store.dto.response.AuthenticationResponse;

import java.text.ParseException;

public interface AuthenticationService {
    IntrospectResponse introspect(IntrospectRequest request) throws ParseException, JOSEException;
    AuthenticationResponse authenticated(AuthenticationRequest request);
    void logout() throws ParseException, JOSEException;
    AuthenticationResponse refreshToken(RefreshTokenRequest request) throws ParseException, JOSEException;
}
