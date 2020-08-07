package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.Country;
import com.ritacle.mhistory.persistence.model.Response;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-integration-test.properties")
public class CountryServiceImplTest {

    @Autowired
    CountryService countryService;

    @Test
    public void saveAllFieldsWrong() {
        Country country = new Country("", "");
        Assert.assertEquals(2, countryService.save(country).getErrors().size());
    }

    @Test
    public void saveWrongCountryCode() {
        Country country = new Country("Australia", "      ");
        Assert.assertEquals(1, countryService.save(country).getErrors().size());
    }

    @Test
    public void saveWrongFullCountryName() {
        Country country = new Country(null, "UA");
        Assert.assertEquals(1, countryService.save(country).getErrors().size());
    }

    @Test
    public void addNewCountry() {
        Country country = new Country("Ukraine", "UA");
        Response<Country> result = countryService.save(country);
        assertEquals("Ukraine", countryService.findCountryByCountryCodeIgnoreCase(country.getCountryCode()).getFullName());
        assertEquals(0, result.getErrors().size());
    }

    @Test
    public void addDuplicateCountry() {
        Country country = new Country("Australia", "AU");
        Response<Country> firstSave = countryService.save(country);
        assertEquals(0, firstSave.getErrors().size());
        assertEquals("Australia", countryService.findCountryByCountryCodeIgnoreCase(country.getCountryCode()).getFullName());
        Response<Country> secondSave = countryService.save(country);
        assertEquals(1, secondSave.getErrors().size());
    }

}