package com.k_konsult.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
@Entity
@Table(name = "area")
public class Area {
    @OneToMany(mappedBy = "area")
    private List<Township> townships;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private String name;

    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<Township> getTownships() {
        return townships;
    }
    public void setTownships(List<Township> townships) {
        this.townships = townships;
    }
}
