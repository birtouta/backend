package com.birtouta.dto;

import com.birtouta.entities.Delivery;
import com.birtouta.entities.OrderProduct;
import com.birtouta.entities.Store;
import com.birtouta.entities.User;

import lombok.Data;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO implements Serializable {

    private Long id;
    private Date createdAt;
    private Date updatedAt;
    private Date receivedAt;
    private int prepared ;
    private Date preparedAt;
    private int delivered ;
    private Date deliveredAt;
    private int approved ;
    private Store store;
    private User user;
    private Date approvedAt;
    private int paid ;
    private Date paidAt;
    private int canceled ;
    private Date canceledAt;
    private int typeDelivery ;
    private List<OrderProduct> orderProducts;
    private Delivery delivery;

}
