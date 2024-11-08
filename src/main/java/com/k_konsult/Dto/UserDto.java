package com.k_konsult.Dto;

import org.modelmapper.ModelMapper;

import com.k_konsult.Entity.User;

public class UserDto {
    private  ModelMapper modelMapper = new ModelMapper();
    private String username;
    private String password;
    private String name;
    private String lastName;


    public void setUsername( String username){
        this.username= username;
    }
    public String getUsername(){
        return this.username;
    }
    public void setPassword(String _password){
        this.password=_password;
    }
    public String getPassword (){
        return this.password;
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
    public User dtoToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public  UserDto entityToDto(User user) {
    return modelMapper.map(user, UserDto.class);
    }
}
