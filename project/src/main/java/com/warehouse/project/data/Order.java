package com.warehouse.project.data;

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

    private Date submitTime;
    private Date acceptTime;
    private Date completedTime;
    OrderStatus orderStatus;
    @ManyToOne
    @JoinColumn(name = "contractor_id")
    private Contractor contractor;

    @ManyToOne
    private Warehouse warehouse;

    @OneToMany(mappedBy = "order")
    private List<ActionCommodity> commodityList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order() {
    }

    public Order(Date submitTime, Contractor contractor, List<ActionCommodity> commodityList) {
        this.submitTime = submitTime;
        this.contractor = contractor;
        this.commodityList = commodityList;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Date getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(Date acceptTime) {
        this.acceptTime = acceptTime;
    }

    public Date getCompletedTime() {
        return completedTime;
    }

    public void setCompletedTime(Date completedTime) {
        this.completedTime = completedTime;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    public List<ActionCommodity> getCommodityList() {
        return commodityList;
    }

    public void setCommodityList(List<ActionCommodity> commodityList) {
        this.commodityList = commodityList;
    }
}
