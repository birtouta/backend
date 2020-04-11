package com.birtouta.dto;

import com.birtouta.entities.Delivery;
import com.birtouta.entities.OrderProduct;
import com.birtouta.entities.Store;
import com.birtouta.entities.User;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class OrderDTO {

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(Date receivedAt) {
        this.receivedAt = receivedAt;
    }

    public int getPrepared() {
        return prepared;
    }

    public void setPrepared(int prepared) {
        this.prepared = prepared;
    }

    public Date getPreparedAt() {
        return preparedAt;
    }

    public void setPreparedAt(Date preparedAt) {
        this.preparedAt = preparedAt;
    }

    public int getDelivered() {
        return delivered;
    }

    public void setDelivered(int delivered) {
        this.delivered = delivered;
    }

    public Date getDeliveredAt() {
        return deliveredAt;
    }

    public void setDeliveredAt(Date deliveredAt) {
        this.deliveredAt = deliveredAt;
    }

    public int getApproved() {
        return approved;
    }

    public void setApproved(int approved) {
        this.approved = approved;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getApprovedAt() {
        return approvedAt;
    }

    public void setApprovedAt(Date approvedAt) {
        this.approvedAt = approvedAt;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    public Date getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(Date paidAt) {
        this.paidAt = paidAt;
    }

    public int getCanceled() {
        return canceled;
    }

    public void setCanceled(int canceled) {
        this.canceled = canceled;
    }

    public Date getCanceledAt() {
        return canceledAt;
    }

    public void setCanceledAt(Date canceledAt) {
        this.canceledAt = canceledAt;
    }

    public int getTypeDelivery() {
        return typeDelivery;
    }

    public void setTypeDelivery(int typeDelivery) {
        this.typeDelivery = typeDelivery;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }
}
