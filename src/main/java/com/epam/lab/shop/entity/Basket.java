package com.epam.lab.shop.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket implements Serializable {

    private final Map<Car, Integer> carInBasketMap = new HashMap<>();

    public Map<Car, Integer> getMap() {
        return new HashMap<>(carInBasketMap);
    }

    public void addCarToBasket(Car car, int quantity) {
        if (carInBasketMap.containsKey(car)) {
            carInBasketMap.put(car, carInBasketMap.get(car) + quantity);
        } else {
            carInBasketMap.put(car, quantity);
        }
    }

    public int getCarQuantity(Car car) {
        return carInBasketMap.get(car);
    }

    public void deleteCarFromBasket(Car car) {
        carInBasketMap.remove(car);
    }

    public void decreaseCarQuantity(Car car, int quantity) {
        if (carInBasketMap.containsKey(car)) {
            if (carInBasketMap.get(car) <= quantity) {
                carInBasketMap.remove(car);
            } else {
                carInBasketMap.put(car, carInBasketMap.get(car) - quantity);
            }
        }
    }

    public List<Car> getCarsFromBasket() {
        return new ArrayList<>(carInBasketMap.keySet());
    }

    public int getCarQuantityFromBasket() {
        return carInBasketMap.values().stream().reduce(0, Integer::sum);
    }

    public float getTotalCarCostFromBasket() {
        return carInBasketMap.isEmpty() ? 0 : carInBasketMap.keySet().stream()
                .map(car -> car.getPrice() * carInBasketMap.get(car)).reduce(0f, Float::sum);
    }

    public void clearBasket() {
        carInBasketMap.clear();
    }
}