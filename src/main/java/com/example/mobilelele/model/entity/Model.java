package com.example.mobilelele.model.entity;

import com.example.mobilelele.model.entity.enums.CategoryType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "models")
public class Model extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryType category;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(nullable = false, name = "start_year")
    private int startYear;

    @Column(name = "end_year")
    private Integer endYear;

    @ManyToOne(optional = false)
    private Brand brand;

    @OneToMany(mappedBy = "model", targetEntity = Offer.class)
    private Set<Offer> offers = new HashSet<>();

    public Set<Offer> getOffers() {
        return offers;
    }

    public Model setOffers(Set<Offer> offers) {
        this.offers = offers;
        return this;
    }

    public String getName() {
        return name;
    }

    public Model setName(String name) {
        this.name = name;
        return this;
    }

    public CategoryType getCategory() {
        return category;
    }

    public Model setCategory(CategoryType category) {
        this.category = category;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Model setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public int getStartYear() {
        return startYear;
    }

    public Model setStartYear(int startYear) {
        this.startYear = startYear;
        return this;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public Model setEndYear(Integer endYear) {
        this.endYear = endYear;
        return this;
    }

    public Brand getBrand() {
        return brand;
    }

    public Model setBrand(Brand brand) {
        this.brand = brand;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Model model = (Model) o;
        return Objects.equals(name, model.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
