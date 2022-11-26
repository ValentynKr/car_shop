package com.epam.lab.shop.factory;

import com.epam.lab.shop.constant.Constant;
import com.epam.lab.shop.entity.AdditionalOrderData;
import com.epam.lab.shop.entity.Basket;
import com.epam.lab.shop.entity.Car;
import com.epam.lab.shop.entity.DeliveryType;
import com.epam.lab.shop.entity.Order;
import com.epam.lab.shop.entity.OrderDetail;
import com.epam.lab.shop.entity.PaymentType;
import com.epam.lab.shop.entity.User;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class OrderFactory {

    public Order getOrderFromUserAdditionalOrderDataBasket(User user, AdditionalOrderData additionalOrderData, Basket basket) {
        int orderId = getRandomOrderId();
        return new Order.Builder()
                .withId(orderId)
                .withPaymentType(PaymentType.valueOf(additionalOrderData.getTypeOfPayment()))
                .withCardCredential(getCardCredential(additionalOrderData))
                .withDeliveryType(DeliveryType.valueOf(additionalOrderData.getTypeOfDelivery()))
                .withDeliveryAddress(additionalOrderData.getAddressForDelivery())
                .withUserId(user.getId())
                .withOrderDetails(getOrderDetails(basket, orderId))
                .withCreationDate(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    private String getCardCredential(AdditionalOrderData additionalOrderData) {
        if (Constant.CARD.equals(additionalOrderData.getTypeOfPayment())) {
            return String.format(Constant.CART_CREDENTIAL_PATTERN, additionalOrderData.getCardCredential(),
                    additionalOrderData.getExpiringDate(), additionalOrderData.getCvvCode());
        }
        return null;
    }

    private List<OrderDetail> getOrderDetails(Basket basket, int orderId) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (Map.Entry<Car, Integer> entry : basket.getMap().entrySet()) {
            OrderDetail orderDetail = new OrderDetail(entry.getKey().getPrice(),
                    entry.getValue(), orderId, entry.getKey().getId());
            orderDetails.add(orderDetail);
        }
        return orderDetails;
    }

    private int getRandomOrderId() {
        return ThreadLocalRandom.current().nextInt(0, 100000 + 1);
    }
}