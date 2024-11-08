package com.k_konsult.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.k_konsult.Dto.PropertyDto;
import com.k_konsult.Dto.PropertyInfoDto;
import com.k_konsult.Entity.Property;
import com.k_konsult.Repository.PropertyRepository;

@Service
public class PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;

    public Map<String, String> CreateProperty( PropertyDto propertyDto){
        Map<String, String> response = new HashMap<>();
        Optional <Property> property = propertyRepository.findByNameProperty(propertyDto.getNameProperty());
        if(property.isPresent()==false){
            Property newProperty = new Property();
            newProperty = propertyDto.dtoToEntity(propertyDto);
            propertyRepository.saveAndFlush(newProperty);
            response.put("message", "Имотът е създаден!");
            return response; 
        }else{
            response.put("message", "Вече съществува такъв имот!");
            return response;  
        } 
    }

    public Map<String, String>  getPropertyid(PropertyDto propertyDto)
    {
        Map<String, String> response = new HashMap<>();
        Optional<Property> findProperty = propertyRepository.findByNameProperty(propertyDto.getNameProperty());
        if(findProperty.isPresent()==true){
        response.put("message" , findProperty.get().getPropertyId()+"_property");
        return response;}
        else{
            response.put("message" , "Няма такъв имот!");
            return response;
        }
    }

    public Map<String , String> updateProperty(PropertyDto propertyDto)
    {
        Map<String, String> response = new HashMap<>();
        Optional<Property> property = propertyRepository.findByNameProperty(propertyDto.getNameProperty());
        if(property.isPresent()==true){
            property.get().setType(propertyDto.getType());
            property.get().setTown(propertyDto.getTown());
            property.get().setNeighborhood(propertyDto.getNeighborhood());
            property.get().setCategory(propertyDto.getCategory());
            property.get().setPrice(propertyDto.getPrice());
            property.get().setPricePerQuadrature(propertyDto.getPricePerQuadrature());
            property.get().setQuadrature(propertyDto.getQuadrature());
            property.get().setConstruction(propertyDto.getConstruction());
            property.get().setTypeOfConstruction(propertyDto.getTypeOfConstruction());
            property.get().setAkt(propertyDto.getAkt());
            property.get().setDescription(propertyDto.getDescription());
            property.get().setYearOfConstruction(propertyDto.getYearOfConstruction());
            property.get().setFloar(propertyDto.getFloar());
            property.get().setFloars(propertyDto.getFloars());
            property.get().setElevator(false);
            property.get().setOwenerName(propertyDto.getOwenerName());
            property.get().setOwnerLastName(propertyDto.getOwnerLastName());
            property.get().setOwnerPhone(propertyDto.getOwnerPhone());
            propertyRepository.save(property.get());
            response.put("message" , "Имота е променен успешно!");
            return response;
        }
        else{
            response.put("message" , "Няма такъв имот!");
            return response;
        }
    }

    public Map<String , String>  deleteProperty(String propertyName){
        Map<String, String> response = new HashMap<>();
        Optional<Property> property = propertyRepository.findByNameProperty(propertyName);
        if(property.isPresent()==true){
            propertyRepository.delete(property.get());
            response.put("message" , "Имотът е изтрит!");
            return response;
        }
        else{
            response.put("message" , "Няма такъв имот!");
            return response;
        }
    }


    public PropertyDto getPropertyByName(String propertyName)
    {
        Optional<Property> property = propertyRepository.findByNameProperty(propertyName);
        if(property.isPresent()==true){
            PropertyDto propertyDto = new PropertyDto();
            return propertyDto.entityToDto(property.get());
    }
        else{
            PropertyDto propertyDto = new PropertyDto();
            propertyDto = null;
            return propertyDto;
        }
    }

    public ArrayList<String> getAll()
    {
        List<Property> property = propertyRepository.findAll();
        ArrayList<String> propertys = new ArrayList<String>();
        for(int i=0; i<property.size() ; i++){
            propertys.add(property.get(i).getNameProperty());
        }
        return propertys;
    }

    public ArrayList<PropertyInfoDto> getAllPropertyDto()
    {
        List<Property> property = propertyRepository.findAll();
        ArrayList<PropertyInfoDto> propertys = new ArrayList<PropertyInfoDto>();
        for(int i=0; i<property.size() ; i++){
            PropertyInfoDto propertyDto  = new PropertyInfoDto();
            propertys.add(propertyDto.entityToDto(property.get(i)));
        }
        return propertys;
    }

    public ArrayList<PropertyInfoDto> getAllPropertyByType(String type)
    {
        List<Property> property = propertyRepository.findAllByType(type);
        ArrayList<PropertyInfoDto> propertys = new ArrayList<PropertyInfoDto>();
        for(int i=0; i<property.size() ; i++){
            PropertyInfoDto propertyDto  = new PropertyInfoDto();
            propertys.add(propertyDto.entityToDto(property.get(i)));
        }
        return propertys;
    } 

    public ArrayList<PropertyInfoDto> getAllPropertyByTypeAndCategory(String type , String category)
    {
        List<Property> property = propertyRepository.findAllByTypeAndCategory(type , category);
        ArrayList<PropertyInfoDto> propertys = new ArrayList<PropertyInfoDto>();
        for(int i=0; i<property.size() ; i++){
            PropertyInfoDto propertyDto  = new PropertyInfoDto();
            propertys.add(propertyDto.entityToDto(property.get(i)));
        }
        return propertys;
    } 

    public ArrayList<PropertyInfoDto> getAllPropertyByCategory(String category)
    {
        List<Property> property = propertyRepository.findAllByCategory(category);
        ArrayList<PropertyInfoDto> propertys = new ArrayList<PropertyInfoDto>();
        for(int i=0; i<property.size() ; i++){
            PropertyInfoDto propertyDto  = new PropertyInfoDto();
            propertys.add(propertyDto.entityToDto(property.get(i)));
        }
        return propertys;
    } 


    public PropertyInfoDto getPropertyInformationByName(String propertyName)
    {
        Optional<Property> property = propertyRepository.findByNameProperty(propertyName);
        if(property.isPresent()==true){
            PropertyInfoDto propertyDto = new PropertyInfoDto();
            return propertyDto.entityToDto(property.get());
    }
        else{
            PropertyInfoDto propertyDto = new PropertyInfoDto();
            propertyDto = null;
            return propertyDto;
        }
    }
}
