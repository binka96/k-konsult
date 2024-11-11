package com.k_konsult.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.k_konsult.Dto.AreaDto;
import com.k_konsult.Dto.TownshipDto;
import com.k_konsult.Service.PlaceService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(path = "/K-Konsult/Place")
public class PlaceControler {
    
    @Autowired
    private PlaceService placeService;

    /*@GetMapping("/read-file")
    public ResponseEntity<String>   readFileAsString() throws IOException {
    
    return ResponseEntity.ok(placeService.addPlaceInDb());
    }*/

    @GetMapping("getAllPlace")
    public ResponseEntity<ArrayList<AreaDto>> getAllPlaces(){
        return ResponseEntity.ok(placeService.getAllPlace());
    }

    @GetMapping("getPlace/id={id}")
    public ResponseEntity<String> getAllPlaces(@PathVariable int id){
        return ResponseEntity.ok(placeService.getPlace(id));
    }

}
