package com.example.elsa_store.dto.response;

import java.time.LocalDate;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RevenueSummaryResponse {
    private LocalDate from;
    private LocalDate to;

    private Long ordersCount;

    private Double grossRevenue; // sum(totalAmount)
    private Double discountTotal; // sum(discountAmount)
    private Double netRevenue; // sum(finalAmount)

    private Double averageOrderValue; // netRevenue / ordersCount
}
