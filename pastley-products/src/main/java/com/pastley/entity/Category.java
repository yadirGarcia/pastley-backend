package com.pastley.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "category")
public class Category implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String status;

    @Column(name = "date_register")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateRegistrer;

    @JsonManagedReference
    @OneToMany(mappedBy = "category")
    private List<Product> products;


    @PrePersist
    public void prePersist() {
        this.dateRegistrer = new Date();
    }

    public Category(){

    }

    public Category(Long id, String name, String statu, Date dateRegistrer) {
        this.id = id;
        this.name = name;
        this.status = statu;
        this.dateRegistrer = dateRegistrer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateRegistrer() {
        return dateRegistrer;
    }

    public void setDateRegistrer(Date dateRegistrer) {
        this.dateRegistrer = dateRegistrer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
