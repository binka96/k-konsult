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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.k_konsult.Dto.InquiryDto;
import com.k_konsult.Service.InquiryService;

@Controller
@CrossOrigin(origins = "https://k-konsult.bg")
@RequestMapping(path = "/K-Konsult/Inquiry")
public class InquiryController {
    
    @Autowired
    private InquiryService inquiryService;

    /*@GetMapping(path = "/Get")
    public ResponseEntity<InquiryDto> get(){
        InquiryDto inquiryDto = new InquiryDto();
        inquiryDto.setName("null");
        inquiryDto.setLastName("null");
        inquiryDto.setEmail("null");
        inquiryDto.setComment("null");
        inquiryDto.setDate(null);
        inquiryDto.setTime(null);
        inquiryDto.setJdprConferm(true);
        return ResponseEntity.ok(inquiryDto);
    }*/

    @PostMapping(path = "/Get/CreateInquiry")
    public ResponseEntity<Map<String, String>> createInquiry(@RequestBody InquiryDto inquiryDto){
        return ResponseEntity.ok(inquiryService.createInquiry(inquiryDto));
    }

    @PostMapping(path = "/Get/CreateInquiry/PropertyName={propertyName}")
    public ResponseEntity<Map<String, String>> createInquiry(@RequestBody InquiryDto inquiryDto , @PathVariable String propertyName){
        return ResponseEntity.ok(inquiryService.createInquiry(inquiryDto , propertyName));
    }
    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping(path = "/GetAllInquiry")
    public ResponseEntity<ArrayList<InquiryDto>> getAllInquiry(){
        return ResponseEntity.ok(inquiryService.getAllInquiry());
    }
    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping(path = "/GetAllInquiry/InquiryId={inquiry_id}")
    public ResponseEntity<InquiryDto> getInquiryByid(@PathVariable int inquiry_id){
        return ResponseEntity.ok(inquiryService.getInquiryById(inquiry_id));
    }
    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping(path="/DeleteInquiry/InquiryId={inquiry_id}")
    public ResponseEntity<Map<String, String>> deleteInquiryId(@PathVariable int inquiry_id)
    {
        return ResponseEntity.ok(inquiryService.deleteInquiryById(inquiry_id));
    }
    
}
