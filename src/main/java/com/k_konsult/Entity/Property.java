package com.k_konsult.Entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "property")
public class Property {
    @OneToMany(mappedBy = "property")
    private List<Inquiry> inquiries;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int propertyId;   
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
    @Lob
    private String description;
    private int yearOfConstruction; 
    private int floar;
    private int floars;
    private boolean   elevator;
    private String owenerName;
    private String ownerLastName;
    private String ownerPhone;
    
    public void setPropertyId(int _ipropertyId){
        this.propertyId = _ipropertyId;
    }

    public int getPropertyId(){
        return this.propertyId;
    }

    public String getNameProperty() {
        return nameProperty;
    }

    public void setNameProperty(String nameProperty) {
        this.nameProperty = nameProperty;
    }
    
    public void setType( String _type){
        this.type= _type;
    }
    public String getType(){
        return this.type;
    }

    public void setTown( String _town){
        this.town= _town;
    }
    public String getTown(){
        return this.town;
    }

    public void setNeighborhood( String _neighborhood){
        this.neighborhood= _neighborhood;
    }
    public String getNeighborhood(){
        return this.neighborhood;
    }

    public void setCategory(String _category){
        this.category=_category;
    }

    public String GetCategory(){
        return this.category;
    }

    public void setPrice( float _price){
        this.price = _price ;
    }
 
    public float getPrice(){
        return this.price;
    }
    
    public void setPricePerQuadrature(float _pricePerQuadrature){
        this.pricePerQuadrature = _pricePerQuadrature;
    }

    public float getPricePerQuadrature(){
        return this.pricePerQuadrature;
    }

    public void setQuadrature(float _quadrature){
        this.quadrature = _quadrature;
    }

    public float getQuadrature(){
        return this.quadrature;
    }

    public void setConstruction(String _construction){
        this.construction=_construction;
    }

    public String getConstruction(){
        return this.construction;
    }

    public void setTypeOfConstruction(String _typeOfConstruction){
        this.typeOfConstruction = _typeOfConstruction;
    }

    public String getTypeOfConstruction(){
        return this.typeOfConstruction;
    }

    public void setAkt(String akt){
        this.akt=akt;
    }

    public String getAkt(){
        return this.akt;
    }

    public void setDescription(String _description){
        this.description = _description;
    }

    public String getDescription(){
        return this.description;
    }
    
    public void setYearOfConstruction(int _yearOfConstruction){
        this.yearOfConstruction = _yearOfConstruction;
    }

    public int getYearOfConstruction(){
        return this.yearOfConstruction;
    }

    public void setFloar( int _floar){
        this.floar = _floar;
    }

    public int getFloar(){
        return this.floar;
    }

    public void setFloars(int _floars){
        this.floars= _floars;
    }

    public int getFloars(){
        return this.floars;
    }

    public void setElevator(boolean _elevator){
        this.elevator=_elevator;
    }

    public boolean getElevator()
    {
        return this.elevator;
    }


    public void setInquiries(List<Inquiry> inquiries) {
        this.inquiries = inquiries;
    }

    public List<Inquiry> getInquiries() {
        return inquiries;
    }

    public String getOwenerName() {
        return owenerName;
    }

    public void setOwenerName(String owenerName) {
        this.owenerName = owenerName;
    }

    public String getOwnerLastName() {
        return ownerLastName;
    }

    public void setOwnerLastName(String ownerLastName) {
        this.ownerLastName = ownerLastName;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

}
