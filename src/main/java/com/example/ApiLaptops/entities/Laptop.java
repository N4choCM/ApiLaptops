package com.example.ApiLaptops.entities;

import javax.persistence.*;

@Entity
@Table(name = "laptops")
public class Laptop {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private Integer ram;
    private Double price;


    // Constructores

    public Laptop() {
    }

    public Laptop(Long id, String brand, String model, Integer ram, Double price) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.ram = ram;
        this.price = price;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}