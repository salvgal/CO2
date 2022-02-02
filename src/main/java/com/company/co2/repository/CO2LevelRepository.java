package com.company.co2.repository;

import com.company.co2.model.CO2Level;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CO2LevelRepository extends JpaRepository<CO2Level, Long> {
    List<CO2Level> findByCityOrderByDetectionDateDesc(String city);

    List<CO2Level> findByCityAndDistrictOrderByDetectionDateDesc(String city, String district);
}