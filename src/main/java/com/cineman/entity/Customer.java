// Customer.java
package com.cineman.entity;

public class Customer extends User {
    private Integer membershipCardId;

    public Customer() {}

    public Integer getMembershipCardId() {
        return membershipCardId;
    }
    public void setMembershipCardId(Integer membershipCardId) {
        this.membershipCardId = membershipCardId;
    }
}
