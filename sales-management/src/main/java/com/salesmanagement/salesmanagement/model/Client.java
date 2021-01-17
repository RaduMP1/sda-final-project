package com.salesmanagement.salesmanagement.model;

import javax.persistence.*;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_name")
    private String clientName;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private Long phone;
    @Column(name = "email")
    private String email;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Client() {
    }

    public Client(String clientName, String address, Long phone, String email) {
        super();
        this.clientName = clientName;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

//    @Override
//    public String toString() {
//        return "Client [id=" + id +
//                ", clientName=" + clientName +
//                ", address=" + address +
//                ", phone=" + phone +
//                ", email=" + email + "]";
//    }
}
