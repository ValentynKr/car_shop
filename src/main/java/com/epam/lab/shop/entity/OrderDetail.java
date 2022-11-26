package com.epam.lab.shop.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class OrderDetail implements Serializable {

    private final int id;
    private final float pricePerOne;
    private final int quantity;
    private final int orderId;
    private final int carId;

    public OrderDetail() {
        this.id = ThreadLocalRandom.current().nextInt(0, 10000 + 1);
        this.pricePerOne = 0f;
        this.quantity = 0;
        this.orderId = 0;
        this.carId = 0;
    }

    public OrderDetail(float pricePerOne, int quantity, int orderId, int carId) {
        this.id = ThreadLocalRandom.current().nextInt(0, 10000 + 1);
        this.pricePerOne = pricePerOne;
        this.quantity = quantity;
        this.orderId = orderId;
        this.carId = carId;
    }

    public OrderDetail(int id, float pricePerOne, int quantity, int orderId, int carId) {
        this.id = id;
        this.pricePerOne = pricePerOne;
        this.quantity = quantity;
        this.orderId = orderId;
        this.carId = carId;
    }

    public int getId() {
        return id;
    }

    public float getPricePerOne() {
        return pricePerOne;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getCarId() {
        return carId;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", pricePerOne=" + pricePerOne +
                ", quantity=" + quantity +
                ", orderId=" + orderId +
                ", carId=" + carId +
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
        OrderDetail that = (OrderDetail) obj;
        return id == that.id && Double.compare(that.pricePerOne, pricePerOne) == 0 && quantity == that.quantity && orderId == that.orderId && carId == that.carId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pricePerOne, quantity, orderId, carId);
    }
}