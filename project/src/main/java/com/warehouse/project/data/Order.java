package com.warehouse.project.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private Date submit_time;
    private Date accept_time;
    private Date completed_time;
    OrderStatus order_status;

    @ManyToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "release_id")
    @JsonIgnore
    Release release;

    public Release getRelease() {
        return release;
    }

    public void setRelease(Release release) {
        this.release = release;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order() {
    }

    public Order(Date submitTime) {
        this.submit_time = submitTime;
    }


    public Date getSubmit_time() {
        return submit_time;
    }

    public void setSubmit_time(Date submit_time) {
        this.submit_time = submit_time;
    }

    public Date getAccept_time() {
        return accept_time;
    }

    public void setAccept_time(Date accept_time) {
        this.accept_time = accept_time;
    }

    public Date getCompleted_time() {
        return completed_time;
    }

    public void setCompleted_time(Date completed_time) {
        this.completed_time = completed_time;
    }

    public OrderStatus getOrder_status() {
        return order_status;
    }

    public void setOrder_status(OrderStatus order_status) {
        this.order_status = order_status;
    }

}
