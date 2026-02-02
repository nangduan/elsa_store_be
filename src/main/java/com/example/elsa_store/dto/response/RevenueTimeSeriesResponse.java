package com.example.elsa_store.dto.response;

import com.example.elsa_store.constant.RevenueGroupBy;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RevenueTimeSeriesResponse {
    private LocalDateTime from;
    private LocalDateTime to;
    private RevenueGroupBy groupBy;
    private List<RevenuePointResponse> points;
}
