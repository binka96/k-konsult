package com.k_konsult.Service;

import java.util.Optional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.k_konsult.Dto.InquiryDto;
import com.k_konsult.Entity.Inquiry;
import com.k_konsult.Entity.Property;
import com.k_konsult.Repository.InquiryRepository;
import com.k_konsult.Repository.PropertyRepository;

@Service
public class InquiryService {
    
    @Autowired
    private InquiryRepository inquiryRepository;
    @Autowired 
    private PropertyRepository propertyRepository;

    public Map<String, String> createInquiry(InquiryDto inquiryDto){
        Map<String, String> response = new HashMap<>();
        Inquiry inquiry = new Inquiry();
        inquiry = inquiryDto.dtoToEntity(inquiryDto);
        inquiryRepository.saveAndFlush(inquiry);
        response.put("message", "Запитването е направено успешно. Наш служител ще се свърже с вас!");
        return response ;
    }


    public Map<String, String> createInquiry(InquiryDto inquiryDto, String propertyName){
        Map<String, String> response = new HashMap<>();
        Optional <Property> property = propertyRepository.findByNameProperty(propertyName);
        if(property.isPresent()==true){
            Inquiry inquiry = new Inquiry();
            inquiry = inquiryDto.dtoToEntity(inquiryDto);
            inquiry.setProperty(property.get());
            inquiryRepository.saveAndFlush(inquiry);
            response.put("message", "Запитването е направено успешно. Наш служител ще се свърже с вас!");
            return response ;
        }else{
            response.put("message", "Имотът за който е напревен запитването липсва!");
            return response ;
        }
    }


    public ArrayList<InquiryDto> getAllInquiry(){
        List<Inquiry> inquiries = inquiryRepository.findAll();
        ArrayList<InquiryDto> inquiryDtos = new ArrayList<InquiryDto>();
        for(int i=0;i<inquiries.size();i++){
            InquiryDto inquiryDto = new InquiryDto();
            inquiryDto = inquiryDto.entityToDto(inquiries.get(i));
            inquiryDtos.add(inquiryDto);
        }
        return inquiryDtos;
    }

    public InquiryDto getInquiryById(int id){
        Optional<Inquiry> inquiry = inquiryRepository.findById(id);
        if(inquiry.isPresent()==true){
            InquiryDto inquiryDto = new InquiryDto();
            inquiryDto = inquiryDto.entityToDto(inquiry.get());
            return inquiryDto;
        }
        {
            InquiryDto inquiryDto = new InquiryDto();
            inquiryDto = null;
            return inquiryDto;
        }
    }

    public Map<String, String> deleteInquiryById(int id)
    {
        Map<String, String> response = new HashMap<>();
        Optional<Inquiry> inquiry = inquiryRepository.findById(id);
        if(inquiry.isPresent()==true){
            inquiryRepository.delete(inquiry.get());
            response.put("message", "Запитването е изтрито!");
            return response;
        }
        {
            response.put("message", "Запитването не е изтрито!");
            return response;
        }
    }
}
