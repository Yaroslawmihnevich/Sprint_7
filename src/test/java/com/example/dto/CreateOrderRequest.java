package com.example.dto;

import com.example.dto.enumeration.Color;

import java.util.List;

public class CreateOrderRequest {

    private final String firstName;
    private final String  lastName;
    private final String  address;
    private final String  metroStation;
    private final String  phone;
    private final Long  rentTime;
    private final String  deliveryDate;
    private final String comment;
    private final List<Color> color;

    public CreateOrderRequest(
            String firstName,
            String lastName,
            String address,
            String metroStation,
            String phone,
            Long rentTime,
            String deliveryDate,
            String comment,
            List<Color> color
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getMetroStation() {
        return metroStation;
    }

    public String getPhone() {
        return phone;
    }

    public Long getRentTime() {
        return rentTime;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public String getComment() {
        return comment;
    }

    public List<Color> getColor() {
        return color;
    }
}
