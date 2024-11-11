package com.k_konsult.Dto;

import org.modelmapper.ModelMapper;

import com.k_konsult.Entity.Place;

public class PlaceDto {
    private  ModelMapper modelMapper = new ModelMapper();
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

    public Place dtoToEntity(PlaceDto placeDto ){
        return modelMapper.map(placeDto, Place.class);
    }

    public  PlaceDto entityToDto(Place place) {
    return modelMapper.map(place, PlaceDto.class);
    }
}
