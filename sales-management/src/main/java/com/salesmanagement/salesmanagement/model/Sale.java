package com.salesmanagement.salesmanagement.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sales_name")
    private String salesName;
    @Column(name = "sales_data")
    private Date salesData;
    @Column(name = "status")
    private String status;
    @Column(name = "observation")
    private String observation;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSalesName() {
        return salesName;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }

    public Date getSalesData() {
        return salesData;
    }

    public void setSalesData(Date salesData) {
        this.salesData = salesData;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }


    public Sale() {
    }

    public Sale(String salesName, Date salesData, String status, String observation) {
        super();
        this.salesName = salesName;
        this.salesData = salesData;
        this.status = status;
        this.observation = observation;
    }
}
