package com.k_konsult.Service;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.k_konsult.Dto.AreaDto;
import com.k_konsult.Dto.PlaceDto;
import com.k_konsult.Dto.TownshipDto;
import com.k_konsult.Entity.Area;
import com.k_konsult.Entity.Place;
import com.k_konsult.Entity.Township;
import com.k_konsult.Repository.AreaRepository;
import com.k_konsult.Repository.PlacaRepository;
import com.k_konsult.Repository.TownshipRepository;

@Service
public class PlaceService {
    @Autowired
    private AreaRepository areaRepository;
    @Autowired
    private TownshipRepository townshipRepository;
    @Autowired 
    private PlacaRepository placaRepository;

    public  String    addPlaceInDb()throws IOException{
        String filePath = "/home/arrow/Desktop/k-konsult/k-konsult/src/main/java/com/k_konsult/Service/example.txt";
        String area;
        String township;
        String place;
        Path path = Paths.get(filePath);
        List<String>  list = Files.readAllLines(path);
        for(int i=0 ; i<list.size();i++){
            String[]  lines = list.get(i).split(";");
            area = lines[1];
            township = lines[2];
            place = lines[0];
            Place place2 = new Place();
            Optional<Township> townShipdb = townshipRepository.findByName(township);
            place2.setName(place);
            place2.setTownship(townShipdb.get());
            placaRepository.save(place2);
         }
        return  "Данните са добавени в базата данни!";
    }

    public ArrayList<AreaDto> getAllPlace(){
        ArrayList<AreaDto> areaDtos = new ArrayList<AreaDto>();
        List<Area> areas = areaRepository.findAll();
        for( int i=0 ; i<areas.size();i++){
            AreaDto area = new AreaDto();
            area.area = areas.get(i).getName();
            ArrayList<TownshipDto> townshipDtos = new ArrayList<TownshipDto>();
            List<Township> townships = townshipRepository.findAllByArea(areas.get(i));
            for(int j=0; j<townships.size();j++){
                TownshipDto townshipDto = new TownshipDto();
                townshipDto.township = townships.get(j).getName();
                List<Place> places = placaRepository.findAllByTownship(townships.get(j));
                for(int k=0;k<places.size();k++){
                    PlaceDto place = new PlaceDto();
                    place = place.entityToDto(places.get(k));
                    townshipDto.place.add(place);
                }
                townshipDtos.add(townshipDto);
            }
            area.townships = townshipDtos;
            areaDtos.add(area);

        }
        return  areaDtos;
    }

    public String getPlace(int id){
        Optional<Place> place = placaRepository.findById(id);
        String string = place.get().getTownship().getArea().getName()  + " общ: " + place.get().getTownship().getName() 
                        + " Населено място: " + place.get().getName();
        return string;
    }

    public String updatePlace(int id , String name){
        Optional<Place> place = placaRepository.findById(id);
        String string = "";
        if(place.isPresent()==true){
            place.get().setName(name);
            placaRepository.save(place.get());
            string = "Населено място е променено!";
        }else{
            string = "Населено място не променено!";
        }
        return string;
    }

    public String updateTownship(String townshipName, String name){
        Optional<Township> township = townshipRepository.findByName(townshipName);
        String string = "";
        if(township.isPresent()==true){
            township.get().setName(name);
            townshipRepository.save(township.get());
            string = "Общината е променена!";
        }else{
            string = "Общината не е променена!";
        }
        return string;
    }

    public String updateArea(String areaName, String name){
        Optional<Area> area = areaRepository.findByName(areaName);
        String string = "";
        if(area.isPresent()==true){
            area.get().setName(name);
            areaRepository.save(area.get());
            string = "Областа е променена!";
        }else{
            string = "Областа не е променена!";
        }
        return string;
    }    

    

}
