
package com.example.elsa_store.dto.request;

import jakarta.validation.constraints.NotBlank;

public class SupplierRequest {

    @NotBlank
    private String name;

    private String phone;
    private String address;
    private String email;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }
}
