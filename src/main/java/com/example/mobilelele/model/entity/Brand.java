package com.example.mobilelele.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "brands")
public class Brand extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "brand", targetEntity = Model.class)
    private Set<Model> models = new HashSet<>();

    public String getName() {
        return name;
    }

    public Brand setName(String name) {
        this.name = name;
        return this;
    }

    public Set<Model> getModels() {
        return models;
    }

    public Brand setModels(Set<Model> models) {
        this.models = models;
        return this;
    }
}
