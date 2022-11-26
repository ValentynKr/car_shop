package com.epam.lab.shop.entity;

import com.epam.lab.shop.constant.Constant;

import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Car implements Serializable {

    private int id;
    private String name;
    private float price;
    private int mileage;
    private float engineVolume;
    private Category category;
    private Manufacturer manufacturer;
    private int productionYear;
    private String imageName;

    public Car() {
        this.id = getRandomId();
        this.name = Constant.DEFAULT_CAR_NAME;
        this.price = 0;
        this.mileage = 0;
        this.engineVolume = 0f;
        this.category = new Category();
        this.manufacturer = new Manufacturer();
        this.productionYear = Constant.DEFAULT_PRODUCTION_YEAR;
        this.imageName = Constant.DEFAULT_IMAGE_NAME + id + Constant.PNG_TYPE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public float getEngineVolume() {
        return engineVolume;
    }

    public void setEngineVolume(float engineVolume) {
        this.engineVolume = engineVolume;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    private int getRandomId() {
        return ThreadLocalRandom.current().nextInt(0, 10000 + 1);
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", mileage=" + mileage +
                ", engineVolume=" + engineVolume +
                ", category='" + category.getName() + '\'' +
                ", manufacturer='" + manufacturer.getName() + '\'' +
                ", productionYear=" + productionYear +
                ", imageName='" + imageName + '\'' +
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
        Car car = (Car) obj;
        return id == car.id && Float.compare(car.price, price) == 0 && mileage == car.mileage && Float.compare(car.engineVolume, engineVolume) == 0 && productionYear == car.productionYear && Objects.equals(name, car.name) && Objects.equals(category, car.category) && Objects.equals(manufacturer, car.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, mileage, engineVolume, category, manufacturer, productionYear);
    }

    public static class Builder {

        private final Car car;

        public Builder() {
            this.car = new Car();
        }

        public Builder withId(int id) {
            car.setId(id);
            return this;
        }

        public Builder withName(String name) {
            car.setName(name);
            return this;
        }

        public Builder withPrice(float price) {
            car.setPrice(price);
            return this;
        }

        public Builder withMileage(int mileage) {
            car.setMileage(mileage);
            return this;
        }

        public Builder withEngineVolume(float engineVolume) {
            car.setEngineVolume(engineVolume);
            return this;
        }

        public Builder withCategory(int categoryId, String category) {
            car.setCategory(new Category(categoryId, category));
            return this;
        }

        public Builder withManufacturer(int manufacturerId, String manufacturer) {
            car.setManufacturer(new Manufacturer(manufacturerId, manufacturer));
            return this;
        }

        public Builder withProductionYear(int productionYear) {
            car.setProductionYear(productionYear);
            return this;
        }

        public Builder withImageName(String imageName) {
            car.setImageName(imageName);
            return this;
        }

        public Car build() {
            return car;
        }
    }
}