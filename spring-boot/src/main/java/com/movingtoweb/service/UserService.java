package com.movingtoweb.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movingtoweb.model.Users;
import com.movingtoweb.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Users> getUsers() {
        return usersRepository.findAll();
    }

    @RequestMapping(value = "/load", method = RequestMethod.POST)
    public List<Users> load(@RequestBody final Users users) {
        List<Users> userses = new ArrayList<>();
        userses.add(users);
        return usersRepository.save(userses);
    }

    @RequestMapping(value = "/multipleData", method = RequestMethod.POST)
    public List<Users> multipleData(@RequestBody final List<Users> users) {
          return usersRepository.save(users);
    }
    
    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public List<Users> getUsers(@PathVariable final String name) {
        return usersRepository.findByName(name);
    }
    
    

    @RequestMapping(value = "/loadInMemory", method = RequestMethod.POST)
    public List<Users> loadInMemory(@RequestBody final String url) throws JsonParseException, JsonMappingException, IOException {
    	System.out.println("URL link ::"+url);
        List<Users> userses = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        //restTemplate.postForObject("/users/load", users, List.class);
        //Users users = (Users) restTemplate.getForObject(url, String.class);
        String jsonData= restTemplate.getForObject(url, String.class);
        System.out.println("Data : " +jsonData);
        Users user = new ObjectMapper().readValue(jsonData, Users.class);
        System.out.println("conveterd object  : " +user.toString());
        usersRepository.save(user);
        userses.add(user);
        return userses ;
    }
}
