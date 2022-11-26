package com.epam.lab.shop.entity;

import java.io.Serializable;

public enum OrderStatus implements Serializable {

    ACCEPTED, CONFIRMED, PREPARED, MOVING, RECEIVED, CANCELLED

}
