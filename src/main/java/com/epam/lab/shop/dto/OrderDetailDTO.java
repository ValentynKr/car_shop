package com.epam.lab.shop.dto;

import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class OrderDetailDTO implements Serializable {

    private int id;
    private float pricePerOne;
    private int quantity;
    private int orderId;
    private int carId;
    private String carName;
    private String carManufacturer;

    public OrderDetailDTO() {
        this.id = ThreadLocalRandom.current().nextInt(0, 10000 + 1);
        this.pricePerOne = 0f;
        this.quantity = 0;
        this.orderId = 0;
        this.carId = 0;
        this.carName = null;
        this.carManufacturer = null;
    }

    public OrderDetailDTO(int id, float pricePerOne, int quantity, int orderId, int carId, String carName, String carManufacturer) {
        this.id = id;
        this.pricePerOne = pricePerOne;
        this.quantity = quantity;
        this.orderId = orderId;
        this.carId = carId;
        this.carName = carName;
        this.carManufacturer = carManufacturer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPricePerOne() {
        return pricePerOne;
    }

    public void setPricePerOne(float pricePerOne) {
        this.pricePerOne = pricePerOne;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarManufacturer() {
        return carManufacturer;
    }

    public void setCarManufacturer(String carManufacturer) {
        this.carManufacturer = carManufacturer;
    }

    @Override
    public String toString() {
        return "OrderDetailDTO{" +
                "id=" + id +
                ", pricePerOne=" + pricePerOne +
                ", quantity=" + quantity +
                ", orderId=" + orderId +
                ", carId=" + carId +
                ", carName='" + carName + '\'' +
                ", carManufacturer='" + carManufacturer + '\'' +
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
        OrderDetailDTO that = (OrderDetailDTO) obj;
        return id == that.id && Float.compare(that.pricePerOne, pricePerOne) == 0 && quantity == that.quantity && orderId == that.orderId && carId == that.carId && Objects.equals(carName, that.carName) && Objects.equals(carManufacturer, that.carManufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pricePerOne, quantity, orderId, carId, carName, carManufacturer);
    }
}
