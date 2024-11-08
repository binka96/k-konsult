package com.k_konsult.Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String username;
    private String password;
    private String name;
    private String lastName;
    private Access access;
    public enum Access{
        MANAGER
    }; 

    public void setUserId(int _userId){
        this.userId = _userId;
    }

    public int getUserId(){
        return this.userId;
    }

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
    public Access getAccess(){
        return this.access;
    }
    public void setAccess(Access _access){
        this.access = _access;
    }

}