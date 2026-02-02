package com.example.elsa_store.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RevenuePointResponse {
    private String period; // ví dụ: 2026-01-31, 2026-01, 2026
    private Long ordersCount;
    private Double netRevenue;
}
