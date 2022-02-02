package com.company.co2;

import com.company.co2.model.CO2Level;
import com.company.co2.repository.CO2LevelRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JPAUnitTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    CO2LevelRepository repository;

    @Test
    public void should_find_no_co2_levels_if_repository_is_empty() {
        Iterable<CO2Level> co2Levels = repository.findAll();

        assertThat(co2Levels).asList().isEmpty();
    }

    @Test
    public void should_store_a_co2_level() {
        CO2Level co2Level = repository.save(new CO2Level("100", "Penzing", "Wien"));

        assertThat(co2Level).hasFieldOrPropertyWithValue("level", "100");
        assertThat(co2Level).hasFieldOrPropertyWithValue("district", "Penzing");
        assertThat(co2Level).hasFieldOrPropertyWithValue("city", "Wien");
    }

    @Test
    public void should_find_all_co2_level() {
        CO2Level co2Level1 = new CO2Level("100", "Penzing", "Wien");
        entityManager.persist(co2Level1);

        CO2Level co2Level2 = new CO2Level("200", "Penzing", "Wien");
        entityManager.persist(co2Level2);

        CO2Level co2Level3 = new CO2Level("300", "Wahring", "Wien");
        entityManager.persist(co2Level3);

        Iterable<CO2Level> tutorials = repository.findAll();

        assertThat(tutorials).asList().hasSize(3).contains(co2Level1, co2Level2, co2Level3);
    }


    @Test
    public void should_find_c02_levels_by_city() {
        CO2Level co2Level1 = new CO2Level("100", "Penzing", "Wien");
        entityManager.persist(co2Level1);

        CO2Level co2Level2 = new CO2Level("300", "Eixample", "Barcelona");
        entityManager.persist(co2Level2);

        CO2Level co2Level3 = new CO2Level("100", "Penzing", "Wien");
        entityManager.persist(co2Level3);

        Iterable<CO2Level> tutorials = repository.findByCityOrderByDetectionDateDesc("Wien");

        assertThat(tutorials).asList().hasSize(2).contains(co2Level1, co2Level3);
    }

    @Test
    public void should_find_c02_levels_by_city_and_district() {
        CO2Level co2Level1 = new CO2Level("100", "Penzing", "Wien");
        entityManager.persist(co2Level1);

        CO2Level co2Level2 = new CO2Level("300", "Eixample", "Barcelona");
        entityManager.persist(co2Level2);

        CO2Level co2Level3 = new CO2Level("100", "Wahring", "Wien");
        entityManager.persist(co2Level3);

        Iterable<CO2Level> tutorials = repository.findByCityAndDistrictOrderByDetectionDateDesc("Wien","Penzing");

        assertThat(tutorials).asList().hasSize(1).contains(co2Level1);
    }
}