package com.epam.lab.shop.entity;

import java.io.Serializable;
import java.util.Objects;

public class AdditionalOrderData implements Serializable {

    private String typeOfPayment;
    private String cardCredential;
    private String expiringDate;
    private String cvvCode;
    private String typeOfDelivery;
    private String addressForDelivery;

    public AdditionalOrderData(String typeOfPayment, String cardCredential, String expiringDate, String cvvCode,
                               String typeOfDelivery, String addressForDelivery) {
        this.typeOfPayment = typeOfPayment;
        this.cardCredential = cardCredential;
        this.expiringDate = expiringDate;
        this.cvvCode = cvvCode;
        this.typeOfDelivery = typeOfDelivery;
        this.addressForDelivery = addressForDelivery;
    }

    public AdditionalOrderData() {
    }

    public String getExpiringDate() {
        return expiringDate;
    }

    public void setExpiringDate(String expiringDate) {
        this.expiringDate = expiringDate;
    }

    public String getCvvCode() {
        return cvvCode;
    }

    public void setCvvCode(String cvvCode) {
        this.cvvCode = cvvCode;
    }

    public String getTypeOfPayment() {
        return typeOfPayment;
    }

    public void setTypeOfPayment(String typeOfPayment) {
        this.typeOfPayment = typeOfPayment;
    }

    public String getCardCredential() {
        return cardCredential;
    }

    public void setCardCredential(String cardCredential) {
        this.cardCredential = cardCredential;
    }

    public String getTypeOfDelivery() {
        return typeOfDelivery;
    }

    public void setTypeOfDelivery(String typeOfDelivery) {
        this.typeOfDelivery = typeOfDelivery;
    }

    public String getAddressForDelivery() {
        return addressForDelivery;
    }

    public void setAddressForDelivery(String addressForDelivery) {
        this.addressForDelivery = addressForDelivery;
    }

    @Override
    public String toString() {
        return "AdditionalOrderData{" +
                "typeOfPayment='" + typeOfPayment + '\'' +
                ", cardCredential='" + cardCredential + '\'' +
                ", expiringDate='" + expiringDate + '\'' +
                ", cvvCode='" + cvvCode + '\'' +
                ", typeOfDelivery='" + typeOfDelivery + '\'' +
                ", addressForDelivery='" + addressForDelivery + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AdditionalOrderData that = (AdditionalOrderData) obj;
        return Objects.equals(typeOfPayment, that.typeOfPayment) && Objects.equals(cardCredential, that.cardCredential) && Objects.equals(expiringDate, that.expiringDate) && Objects.equals(cvvCode, that.cvvCode) && Objects.equals(typeOfDelivery, that.typeOfDelivery) && Objects.equals(addressForDelivery, that.addressForDelivery);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeOfPayment, cardCredential, expiringDate, cvvCode, typeOfDelivery, addressForDelivery);
    }
}