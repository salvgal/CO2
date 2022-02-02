package com.company.co2.controller;


import com.company.co2.model.CO2Level;
import com.company.co2.repository.CO2LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CO2LevelController {

    @Autowired
    CO2LevelRepository CO2LevelRepository;

    @GetMapping("/co2/read")
    public ResponseEntity<List<CO2Level>> getAllCO2Levels(@RequestParam(required = false) String district) {
        List<CO2Level> CO2Levels = new ArrayList<CO2Level>();

        //find the city by the username
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String city;
        if (principal instanceof UserDetails) {
            city = ((UserDetails) principal).getUsername();
        } else {
            city = principal.toString();
        }

        //admin get all the data
        if (city.equals("admin")) {
            CO2LevelRepository.findAll().forEach(CO2Levels::add);
        } else {
            //get only data for specified district, if passed as parameter
            if (district != null && !district.equals("")) {
                CO2LevelRepository.findByCityAndDistrictOrderByDetectionDateDesc(city, district).forEach(CO2Levels::add);
            } else {
                //get all data for the city
                CO2LevelRepository.findByCityOrderByDetectionDateDesc(city).forEach(CO2Levels::add);
            }
        }

        if (CO2Levels.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(CO2Levels, HttpStatus.OK);
    }

    @PostMapping("/co2/create")
    public ResponseEntity<CO2Level> createCO2Level(@RequestBody CO2Level cO2Level) {
        CO2Level _CO2Level = CO2LevelRepository
                .save(new CO2Level(cO2Level.getLevel(), cO2Level.getDistrict(), cO2Level.getCity()));
        return new ResponseEntity<>(_CO2Level, HttpStatus.CREATED);
    }

}
