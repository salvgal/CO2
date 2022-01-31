package com.company.co2.controller;


import com.company.co2.model.CO2Level;
import com.company.co2.repository.CO2LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CO2LevelController {

    @Autowired
    CO2LevelRepository CO2LevelRepository;

    @GetMapping("/co2")
    public ResponseEntity<List<CO2Level>> getAllCO2Levels(@RequestParam(required = false) String city) {
        try {
            List<CO2Level> CO2Levels = new ArrayList<CO2Level>();

            if (city == null)
                CO2LevelRepository.findAll().forEach(CO2Levels::add);
            else
                CO2LevelRepository.findByCityOrderByDetectionDateDesc(city).forEach(CO2Levels::add);

            if (CO2Levels.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(CO2Levels, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/co2")
    public ResponseEntity<CO2Level> createCO2Level(@RequestBody CO2Level cO2Level) {
        try {
            CO2Level _CO2Level = CO2LevelRepository
                    .save(new CO2Level(cO2Level.getLevel(), cO2Level.getDistrict(), cO2Level.getCity()));
            return new ResponseEntity<>(_CO2Level, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/co2/{id}")
    public ResponseEntity<HttpStatus> deleteCO2Level(@PathVariable("id") long id) {
        try {
            CO2LevelRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/co2")
    public ResponseEntity<HttpStatus> deleteAllCO2Levels() {
        try {
            CO2LevelRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
