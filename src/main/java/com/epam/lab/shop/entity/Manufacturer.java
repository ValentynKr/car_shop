package com.epam.lab.shop.entity;

import com.epam.lab.shop.constant.Constant;

import java.io.Serializable;
import java.util.Objects;

public class Manufacturer implements Serializable {

    private int id;
    private String name;

    public Manufacturer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Manufacturer() {
        this.id = 0;
        this.name = Constant.DEFAULT_NAME;
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

    @Override
    public String toString() {
        return "Manufacturer{" +
                "id=" + id +
                ", name='" + name + '\'' +
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
        Manufacturer manufacturer = (Manufacturer) obj;
        return id == manufacturer.id && Objects.equals(name, manufacturer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}