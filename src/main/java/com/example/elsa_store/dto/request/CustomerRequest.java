
package com.example.elsa_store.dto.request;

import java.time.LocalDate;

public class CustomerRequest {

    private Long userId;
    private Integer gender;
    private LocalDate dob;

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }

    public Integer getGender() { return gender; }

    public void setGender(Integer gender) { this.gender = gender; }

    public LocalDate getDob() { return dob; }

    public void setDob(LocalDate dob) { this.dob = dob; }
}
