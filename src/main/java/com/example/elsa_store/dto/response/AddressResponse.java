
package com.example.elsa_store.dto.response;

public class AddressResponse {

    private Long id;
    private Long customerId;
    private String fullName;
    private String phone;
    private String province;
    private String district;
    private String ward;
    private String detail;
    private Boolean isDefault;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Long getCustomerId() { return customerId; }

    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public String getFullName() { return fullName; }

    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getProvince() { return province; }

    public void setProvince(String province) { this.province = province; }

    public String getDistrict() { return district; }

    public void setDistrict(String district) { this.district = district; }

    public String getWard() { return ward; }

    public void setWard(String ward) { this.ward = ward; }

    public String getDetail() { return detail; }

    public void setDetail(String detail) { this.detail = detail; }

    public Boolean getIsDefault() { return isDefault; }

    public void setIsDefault(Boolean isDefault) { this.isDefault = isDefault; }
}
