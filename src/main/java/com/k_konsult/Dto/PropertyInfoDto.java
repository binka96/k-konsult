package com.k_konsult.Dto;

import org.modelmapper.ModelMapper;

import com.k_konsult.Entity.Property;

public class PropertyInfoDto {
     private  ModelMapper modelMapper = new ModelMapper();
    private String nameProperty;
    private String type;
    private String town;
    private String neighborhood;
    private String category;
    private float price;
    private float pricePerQuadrature;
    private float quadrature;
    private String construction;
    private String typeOfConstruction;
    private String akt;
    private String description;
    private int yearOfConstruction; 
    private int floar;
    private int floars;
    private boolean   elevator;

    public String getNameProperty() {
        return nameProperty;
    }

    public void setNameProperty(String nameProperty) {
        this.nameProperty = nameProperty;
    }
    
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getTown() {
        return town;
    }
    public void setTown(String town) {
        this.town = town;
    }
    public String getNeighborhood() {
        return neighborhood;
    }
    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public float getPricePerQuadrature() {
        return pricePerQuadrature;
    }
    public void setPricePerQuadrature(float pricePerQuadrature) {
        this.pricePerQuadrature = pricePerQuadrature;
    }
    public float getQuadrature() {
        return quadrature;
    }
    public void setQuadrature(float quadrature) {
        this.quadrature = quadrature;
    }
    public String getConstruction() {
        return construction;
    }
    public void setConstruction(String construction) {
        this.construction = construction;
    }
    public String getTypeOfConstruction() {
        return typeOfConstruction;
    }
    public void setTypeOfConstruction(String typeOfConstruction) {
        this.typeOfConstruction = typeOfConstruction;
    }
    public String getAkt() {
        return akt;
    }
    public void setAkt(String akt) {
        this.akt = akt;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getYearOfConstruction() {
        return yearOfConstruction;
    }
    public void setYearOfConstruction(int yearOfConstruction) {
        this.yearOfConstruction = yearOfConstruction;
    }
    public int getFloar() {
        return floar;
    }
    public void setFloar(int floar) {
        this.floar = floar;
    }
    public int getFloars() {
        return floars;
    }
    public void setFloars(int floars) {
        this.floars = floars;
    }
    public boolean isElevator() {
        return elevator;
    }
    public void setElevator(boolean elevator) {
        this.elevator = elevator;
    }

    
    public Property dtoToEntity(PropertyInfoDto propertyDto) {
        return modelMapper.map(propertyDto, Property.class);
    }

    public  PropertyInfoDto entityToDto(Property property) {
    return modelMapper.map(property, PropertyInfoDto.class);
    }
}
