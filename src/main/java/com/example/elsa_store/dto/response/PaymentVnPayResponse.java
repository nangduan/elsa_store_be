package com.example.elsa_store.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentVnPayResponse {
    String code = "oke";
    String message = "success";
    String paymentUrl;
}
