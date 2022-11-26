package com.epam.lab.shop.dto;

import com.epam.lab.shop.entity.DeliveryType;
import com.epam.lab.shop.entity.OrderStatus;
import com.epam.lab.shop.entity.PaymentType;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class OrderDTO implements Serializable {

    private int id;
    private OrderStatus orderStatus;
    private String detail;
    private PaymentType paymentType;
    private String cardCredential;
    private DeliveryType deliveryType;
    private String deliveryAddress;
    private int userId;
    private List<OrderDetailDTO> orderDetails;
    private Timestamp creationDate;

    public OrderDTO() {
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

    public List<OrderDetailDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailDTO> orderDetails) {
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
        return "OrderDTO{" +
                "id=" + id +
                ", orderStatus=" + orderStatus +
                ", detail='" + detail + '\'' +
                ", paymentType=" + paymentType +
                ", cardCredential='" + cardCredential + '\'' +
                ", deliveryType=" + deliveryType +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", userId=" + userId +
                ", orderDetails=" + orderDetails +
                ", creationDate=" + creationDate +
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
        OrderDTO orderDTO = (OrderDTO) obj;
        return id == orderDTO.id && userId == orderDTO.userId && orderStatus == orderDTO.orderStatus && Objects.equals(detail, orderDTO.detail) && paymentType == orderDTO.paymentType && Objects.equals(cardCredential, orderDTO.cardCredential) && deliveryType == orderDTO.deliveryType && Objects.equals(deliveryAddress, orderDTO.deliveryAddress) && Objects.equals(orderDetails, orderDTO.orderDetails) && Objects.equals(creationDate, orderDTO.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderStatus, detail, paymentType, cardCredential, deliveryType, deliveryAddress, userId, orderDetails, creationDate);
    }
}