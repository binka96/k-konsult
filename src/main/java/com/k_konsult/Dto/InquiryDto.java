package com.k_konsult.Dto;

import java.sql.Date;
import java.sql.Time;
import org.modelmapper.ModelMapper;
import com.k_konsult.Entity.Inquiry;
import com.k_konsult.Entity.Property;

public class InquiryDto {
    private  ModelMapper modelMapper = new ModelMapper();
    private int inquiryId;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String comment;
    private Date date;
    private Time time;
    private boolean jdprConferm;
    private Property property;
    public void setInquiryId(int inquiryId){
        this.inquiryId = inquiryId;
    }

    public int getInquiryId(){
        return this.inquiryId;
    }
    public void setName( String _name){
        this.name= _name;
    }
    public String getName(){
        return this.name;
    }
    public void setLastName(String _lastname){
        this.lastName=_lastname;
    }
    public String getLastName (){
        return this.lastName;
    }
    public void setEmail(String _email){
        this.email = _email;
    }
    public String getEmail(){
        return this.email;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
    public void setJdprConferm(boolean jdprConferm) {
        this.jdprConferm = jdprConferm;
    }

    public boolean isJdprConferm() {
        return jdprConferm;
    }


    public void setProperty(Property property) {
        this.property = property;
    }

    public Property getProperty() {
        return property;
    }
    
    public Inquiry dtoToEntity(InquiryDto inquiryDto) {
        return modelMapper.map(inquiryDto, Inquiry.class);
    }

    public  InquiryDto entityToDto(Inquiry inquiry) {
    return modelMapper.map(inquiry, InquiryDto.class);
    }
}
