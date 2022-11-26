package com.epam.lab.shop.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Order implements Serializable {

    private int id;
    private OrderStatus orderStatus;
    private String detail;
    private PaymentType paymentType;
    private String cardCredential;
    private DeliveryType deliveryType;
    private String deliveryAddress;
    private int userId;
    private List<OrderDetail> orderDetails;
    private Timestamp creationDate;

    public Order() {
        this.id = ThreadLocalRandom.current().nextInt(0, 100000 + 1);
        this.orderStatus = OrderStatus.ACCEPTED;
        this.detail = null;
        this.paymentType = PaymentType.CASH;
        this.cardCredential = null;
        this.deliveryType = DeliveryType.EXW;
        this.deliveryAddress = null;
        this.userId = 0;
        this.orderDetails = new ArrayList<>();
        this.creationDate = new Timestamp(System.currentTimeMillis());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public String getCardCredential() {
        return cardCredential;
    }

    public void setCardCredential(String cardCredential) {
        this.cardCredential = cardCredential;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderStatus=" + orderStatus +
                ", detail='" + detail + '\'' +
                ", paymentType=" + paymentType +
                ", cardCredential='" + cardCredential + '\'' +
                ", deliveryType=" + deliveryType +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", userId=" + userId +
                ", orderDetails=" + orderDetails +
                ", createdDate=" + creationDate +
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
        Order order = (Order) obj;
        return id == order.id && userId == order.userId && orderStatus == order.orderStatus && Objects.equals(detail, order.detail) && paymentType == order.paymentType && Objects.equals(cardCredential, order.cardCredential) && deliveryType == order.deliveryType && Objects.equals(deliveryAddress, order.deliveryAddress) && Objects.equals(orderDetails, order.orderDetails) && Objects.equals(creationDate, order.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderStatus, detail, paymentType, cardCredential, deliveryType, deliveryAddress, userId, orderDetails, creationDate);
    }

    public static class Builder {

        private final Order order;

        public Builder() {
            this.order = new Order();
        }

        public Builder withId(int id) {
            order.setId(id);
            return this;
        }

        public Builder withOrderStatus(OrderStatus orderStatus) {
            order.setOrderStatus(orderStatus);
            return this;
        }

        public Builder withDetail(String detail) {
            order.setDetail(detail);
            return this;
        }

        public Builder withPaymentType(PaymentType paymentType) {
            order.setPaymentType(paymentType);
            return this;
        }

        public Builder withCardCredential(String cardCredential) {
            order.setCardCredential(cardCredential);
            return this;
        }

        public Builder withDeliveryType(DeliveryType deliveryType) {
            order.setDeliveryType(deliveryType);
            return this;
        }

        public Builder withDeliveryAddress(String deliveryAddress) {
            order.setDeliveryAddress(deliveryAddress);
            return this;
        }

        public Builder withUserId(int userId) {
            order.setUserId(userId);
            return this;
        }

        public Builder withOrderDetails(List<OrderDetail> orderDetails) {
            order.setOrderDetails(orderDetails);
            return this;
        }

        public Builder withCreationDate(Timestamp creationDate) {
            order.setCreationDate(creationDate);
            return this;
        }

        public Order build() {
            return order;
        }
    }
}