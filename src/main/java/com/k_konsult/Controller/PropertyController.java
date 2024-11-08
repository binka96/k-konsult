package com.k_konsult.Controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.k_konsult.Dto.PropertyDto;
import com.k_konsult.Dto.PropertyInfoDto;
import com.k_konsult.Service.PropertyService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(path = "/K-Konsult/Property")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    /*@GetMapping(path="/Get")
    public ResponseEntity<PropertyDto> GetUser(){
        PropertyDto propertyDto = new PropertyDto();
        propertyDto.setNameProperty("null");
        propertyDto.setType("null");
        propertyDto.setTown("null");
        propertyDto.setNeighborhood("null");
        propertyDto.setCategory("null");
        propertyDto.setPrice(0);
        propertyDto.setPricePerQuadrature(0);
        propertyDto.setQuadrature(0);
        propertyDto.setConstruction("null");
        propertyDto.setTypeOfConstruction("null");
        propertyDto.setAkt("null");
        propertyDto.setDescription("null");
        propertyDto.setYearOfConstruction(0);
        propertyDto.setFloar(0);
        propertyDto.setFloars(0);
        propertyDto.setElevator(false);
        propertyDto.setOwenerName("null");
        propertyDto.setOwnerLastName("null");
        propertyDto.setOwnerPhone("null");
        return ResponseEntity.ok(propertyDto);
    } */
    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping(path = "/CreateProperty")
    public ResponseEntity<Map<String, String>> CreateProperty(@RequestBody PropertyDto propertyDto){
        return ResponseEntity.ok(propertyService.CreateProperty(propertyDto));
    }
    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping(path = "/TakePropertyId")
    public ResponseEntity< Map<String, String> >  takePropertyid(@RequestBody PropertyDto propertyDto){
        return ResponseEntity.ok(propertyService.getPropertyid(propertyDto));
    }
    @PreAuthorize("hasRole('MANAGER')")
    @PutMapping(path = "/Update")
    public ResponseEntity<Map<String , String>> updateProperty(@RequestBody PropertyDto propertyDto){
        return ResponseEntity.ok(propertyService.updateProperty(propertyDto));
    }
    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping(path = "/Delete/property={propertyName}")
    public ResponseEntity<Map<String , String>> deleteProperty(@PathVariable String propertyName){
        return ResponseEntity.ok(propertyService.deleteProperty(propertyName));
    }
    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping(path = "/GetPropertyByName")
    public ResponseEntity<PropertyDto> getPropertyByName(@RequestBody String propertyName){
        return ResponseEntity.ok(propertyService.getPropertyByName(propertyName));
    }

    @PostMapping(path = "/Get/PropertyInformationByName")
    public ResponseEntity<PropertyInfoDto> getPropertyInformationByName(@RequestBody String propertyName){
        return ResponseEntity.ok(propertyService.getPropertyInformationByName(propertyName));
    }


    @GetMapping(path = "/Get/AllProperty")
    public ResponseEntity<ArrayList<String>> getAllProperty(){
        return ResponseEntity.ok(propertyService.getAll());
    }

    
    @GetMapping(path = "/Get/Properties")
    public ResponseEntity<ArrayList<PropertyInfoDto>> getProperties(){
        return ResponseEntity.ok(propertyService.getAllPropertyDto());
    }

    @GetMapping(path = "/Get/PropertiesByType/type={type}")
    public ResponseEntity<ArrayList<PropertyInfoDto>> getProperties(@PathVariable String type){
        return ResponseEntity.ok(propertyService.getAllPropertyByType(type));
    }

    @GetMapping(path = "/Get/PropertiesByTypeAndCategory/type={type}&category={category}")
    public ResponseEntity<ArrayList<PropertyInfoDto>> getProperties(@PathVariable String type ,@PathVariable String category  ){
        return ResponseEntity.ok(propertyService.getAllPropertyByTypeAndCategory(type , category));
    }

    @GetMapping(path = "/Get/PropertiesByCategory/category={category}")
    public ResponseEntity<ArrayList<PropertyInfoDto>> getPropertiesByCategory(@PathVariable String category ){
        return ResponseEntity.ok(propertyService.getAllPropertyByCategory(category));
    }
}
