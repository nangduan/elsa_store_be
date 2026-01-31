
package com.example.elsa_store.dto.request;

import jakarta.validation.constraints.NotNull;

public class InventoryRequest {

    @NotNull
    private Long productVariantId;

    private Integer quantity;
    private Integer minQuantity;

    public Long getProductVariantId() { return productVariantId; }

    public void setProductVariantId(Long productVariantId) { this.productVariantId = productVariantId; }

    public Integer getQuantity() { return quantity; }

    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Integer getMinQuantity() { return minQuantity; }

    public void setMinQuantity(Integer minQuantity) { this.minQuantity = minQuantity; }
}
