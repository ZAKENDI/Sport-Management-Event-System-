package com.example.myproj.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;             // Название товара
    private String releaseDate;       // Дата выпуска товара
    private String manufacturer;      // Производитель товара
    private String expirationDate;    // Здесь хранится ссылка на изображение товара
    private double price;             // Цена товара

    // Геттеры и сеттеры
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * Возвращает URL изображения товара, хранимого в поле expirationDate.
     * На самом деле это не дата, а URL изображения.
     */
    public String getExpirationDate() {
        return expirationDate;
    }

    /**
     * Устанавливает URL изображения товара (хранится в поле expirationDate).
     */
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}