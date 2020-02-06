package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Cannot be empty")
    @Length(min = 2, max = 100, message = "2 < Name < 100")
    private String name;

    @NotBlank(message = "Cannot be empty")
    @Length(min = 2, max = 1000, message = "2 < Address < 1000")
    private String address;

    public Company() {
    }

    public Company(String name, String telNumber) {
        this.name = name;
        this.address = telNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
