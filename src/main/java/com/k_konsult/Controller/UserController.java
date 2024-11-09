package com.k_konsult.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Map;
import com.k_konsult.Dto.UserDto;
import com.k_konsult.Dto.UserPasswordDto;
import com.k_konsult.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
@Controller
@CrossOrigin(origins = "https://k-konsult.bg")
@RequestMapping(path = "/K-Konsult/User")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;
    /*@GetMapping(path="/Get")
    public ResponseEntity<UserDto> GetUser(){
        UserDto userDto = new UserDto();
        userDto.setUsername("null");
        userDto.setPassword("null");
        userDto.setName("null");
        userDto.setLastName("null");
        return ResponseEntity.ok(userDto);
    }*/
    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping(path="/CreateUser")
    public ResponseEntity<Map<String, String>> CreateUser(@RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.CreateUser(userDto));
    }

    @PostMapping(path="/LogIn")
    public ResponseEntity<Map<String, String>> LogIn(@RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.LogIn(userDto));
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PutMapping(path="/UpdatePassword")
    public ResponseEntity<Map<String, String>> UpdatePassword(@RequestBody UserPasswordDto userPasswordDto){
        return ResponseEntity.ok(userService.UpdatePassword(userPasswordDto , request));
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping(path="/DeletMyAcount")
    public ResponseEntity<Map<String, String>> DeletMyAcount(@RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.DeleteMyAccount(userDto , request));
    }


    @PostMapping(path = "/LogRequest")
    public ResponseEntity<Map<String, String>>  getAccess(@RequestBody String  username){
        return ResponseEntity.ok(userService.SessionRequest(username , request));
    }
    /*@PostMapping(path = "/GetUserInformarion")
    public ResponseEntity<UserDto> getUserInformation(@RequestBody String username){
        return ResponseEntity.ok(userService.getUserInformation(username));
    }*/
}
