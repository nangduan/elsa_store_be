package com.example.elsa_store.dto.request;

import java.util.List;

import jakarta.validation.constraints.NotNull;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {

    private Long customerId;
    private Long userId;
    private Long addressId;

    @NotNull
    private List<OrderItemRequest> items;

    public static class OrderItemRequest {
        @NotNull
        private Long productVariantId;

        @NotNull
        private Integer quantity;

        public Long getProductVariantId() {
            return productVariantId;
        }

        public void setProductVariantId(Long productVariantId) {
            this.productVariantId = productVariantId;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }
    }
}
