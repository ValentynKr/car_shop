package com.epam.lab.shop.dto;

import com.epam.lab.shop.constant.Constant;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class CarFilterDTO implements Serializable {

    private String name;
    private String manufacturer;
    private List<String> category;
    private Float minPrice;
    private Float maxPrice;
    private String sortBy;
    private Integer pageSize;
    private Integer page;

    public CarFilterDTO() {
        this.pageSize = Constant.DEFAULT_PAGE_SIZE;
        this.page = Constant.DEFAULT_PAGE_NUMBER;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public Float getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Float minPrice) {
        this.minPrice = minPrice;
    }

    public Float getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Float maxPrice) {
        this.maxPrice = maxPrice;
    }

    @Override
    public String toString() {
        return "CarFilterDTO{" +
                "name='" + name + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", category=" + category +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", sortBy='" + sortBy + '\'' +
                ", pageSize=" + pageSize +
                ", page=" + page +
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
        CarFilterDTO that = (CarFilterDTO) obj;
        return Objects.equals(name, that.name) && Objects.equals(manufacturer, that.manufacturer) && Objects.equals(category, that.category) && Objects.equals(minPrice, that.minPrice) && Objects.equals(maxPrice, that.maxPrice) && Objects.equals(sortBy, that.sortBy) && Objects.equals(pageSize, that.pageSize) && Objects.equals(page, that.page);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, manufacturer, category, minPrice, maxPrice, sortBy, pageSize, page);
    }
}