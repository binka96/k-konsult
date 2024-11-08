package com.k_konsult.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.k_konsult.Dto.UserDto;
import com.k_konsult.Dto.UserPasswordDto;
import com.k_konsult.Entity.User;
import com.k_konsult.Entity.User.Access;
import com.k_konsult.Repository.UserRepository;
import com.k_konsult.Security.JWTUtil;

import java.util.Map;
import java.util.HashMap;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

@Service
public class UserService {
    @Autowired
     private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtil jwtUtil;
     public Map<String, String>  CreateUser(UserDto newUserDto){
        Map<String, String> response = new HashMap<>();
        Optional <User> user = userRepository.findByUsername(newUserDto.getUsername()); 
        if(user.isPresent()==false){
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(newUserDto.getPassword());
            User newUser = new User();
            newUser = newUserDto.dtoToEntity(newUserDto);
            newUser.setPassword(hashedPassword);
            newUser.setAccess(Access.MANAGER);
            userRepository.saveAndFlush(newUser);
            response.put("message", "Новият потребител е създаден!");
            return response;
        }
        else 
        {        
            response.put("message", "Потребителя вече съществува!");
            return response;
        }
    }

    public  Map<String, String>  LogIn(UserDto userDto){
        Map<String, String> response = new HashMap<>();
        try {
            Optional<User> user = userRepository.findByUsername(userDto.getUsername());
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if(passwordEncoder.matches(userDto.getPassword(), user.get().getPassword())&&user.get().getAccess()==Access.MANAGER){
            // Authenticate user
            Authentication authentication = 
                authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword())
                );
            SecurityContextHolder.getContext().setAuthentication(authentication);
         // If authentication is successful, generate a token
            if (authentication.isAuthenticated()) {
                String token = jwtUtil.generateToken(userDto.getUsername() , userDto.getPassword());
                response.put("token", token);
                response.put("message", "Успеш вход!");
                response.put("access", user.get().getAccess().toString());
            } else {
                response.put("message", "Грешно потребителско име или парола!");
            }
            }
            else {
                response.put("message", "Грешно потребителско име или парола!");
            }
        } catch (BadCredentialsException e) {
            response.put("message", "Грешно потребителско име или парола!");
        }catch (Exception e) {
            response.put("message", "Грешно потребителско име или парола!");
        }
        return response;
    }


        public Map<String, String>  DeleteMyAccount(UserDto userDto , HttpServletRequest request){
        Optional<User>  deleteUser =  userRepository.findByUsername(userDto.getUsername());
        Map<String, String> response = new HashMap<>();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String authorizationHeader = request.getHeader("Authorization");
        String token = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7); // Remove the "Bearer " prefix
        }
        if(token!= null){
        try{    
            String tokenUsername = jwtUtil.extractUsername(token);
            if (!userDto.getUsername().equals(tokenUsername)) {
                response.put("message", "Unauthorized access!");
                return response;
            }
            else if(deleteUser.isPresent()==true){
            if(passwordEncoder.matches(userDto.getPassword(), deleteUser.get().getPassword())){
                userRepository.delete(deleteUser.get());
                response.put("message", "Потребителя  е изтрит!");
                return response; 
            }
            else{
                response.put("message", "Потребителя не е изтрит!");
                return response;  
            }
            }
             else{
                response.put("message", "Потребителя не е изтрит!");
                return response;  
            }
        }
         catch(JWTDecodeException e) {
            response.put("message", "Invalid token!");
            return response;
        }
        }
        else {
            response.put("message", "No token provided!");
            return response;
        }
    }

    public Map<String, String> UpdatePassword(UserPasswordDto userPassword, HttpServletRequest request){
        Map<String, String> response = new HashMap<>();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String newPassword = passwordEncoder.encode(userPassword.new_password);
        UserDto user = new UserDto();
        user = userPassword.userDto;
        Optional<User> updateUser = userRepository.findByUsername(user.getUsername());
        
        String authorizationHeader = request.getHeader("Authorization");
        String token = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7); // Remove the "Bearer " prefix
        }
        
        if (token != null) {
            try {
                String tokenUsername = jwtUtil.extractUsername(token);
                if (!user.getUsername().equals(tokenUsername)) {
                    response.put("message", "Unauthorized access!");
                    return response;
                }
                else if(updateUser.isPresent()==true){
                    if(passwordEncoder.matches(user.getPassword(), updateUser.get().getPassword()))
                    {
                        updateUser.get().setPassword(newPassword);
                        userRepository.save(updateUser.get());
                        response.put("message", "Новата парола е променена");
                        return response;
                    }
                    else{
                        response.put("message", "Грешна парола или потребителско име!");
                        return response;
                    }
                }
                else{
                    response.put("message", "Грешна парола или потребителско име!");
                    return response;
                }
            } catch (JWTDecodeException e) {
                response.put("message", "Invalid token!");
                return response;
            }
        } else {
            response.put("message", "No token provided!");
            return response;
        }
    }

    public UserDto getUserInformation(String username){
        Optional <User> user = userRepository.findByUsername(username);
        if(user.isPresent()==true){
            UserDto userDto = new UserDto();
            userDto = userDto.entityToDto(user.get());
            return userDto;
        }
        else{
            UserDto userDto = new UserDto();
            userDto = null;
            return userDto;
        }
    }

    public Map<String, String>  SessionRequest(String username , HttpServletRequest request){
        Map<String, String> response = new HashMap<>();
        String authorizationHeader = request.getHeader("Authorization");
        String token = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7); // Remove the "Bearer " prefix
        }
        if (token != null){
            try{
                boolean isTokenExpired = jwtUtil.isTokenExpiredDate(token);
                if(isTokenExpired !=false){
                    String tokenUsername = jwtUtil.extractUsername(token);
                    String tokenPassword = jwtUtil.extractPassword(token);
                    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                    Optional <User> user = userRepository.findByUsername(tokenUsername);
                    if(user.isPresent()==true && tokenUsername.equals(username)){
                        if(passwordEncoder.matches(tokenPassword , user.get().getPassword())==true){
                            response.put("Username", user.get().getUsername());
                            response.put("Access", user.get().getAccess().toString());
                            return response; 
                        }else{
                            response.put("message", "No token provided!");
                            return response; 
                        }
                    }
                    else{
                    response.put("message", "No token provided!");
                    return response; 
                }
                }
                else{
                    response.put("message", "No token provided!");
                    return response; 
                }
            } catch (JWTDecodeException e) {
                response.put("message", "Invalid token!");
                return response;
            }
        }
        response.put("message", "No token provided!");
        return response;
        
    }
}
