package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.Country;
import com.ritacle.mhistory.persistence.model.InputError;
import com.ritacle.mhistory.persistence.model.Response;
import com.ritacle.mhistory.persistence.repository.CountryRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    CountryRepository countryRepository;

    @Override
    public Response<Country> save(Country country) {

        List<InputError> errors = validateInput(country);

        if (!errors.isEmpty()) {
            return new Response<>(country, errors);
        }

        if (findCountryByCountryCodeIgnoreCase(country.getCountryCode()) != null) {
            return new Response<>(country, new LinkedList<>(Collections.singleton(new InputError("country", "Country with such code already exists"))));
        }

        return new Response<>(countryRepository.save(country), errors);
    }

    private List<InputError> validateInput(Country country) {
        List<InputError> errors = new LinkedList<>();
        if (StringUtils.isAllBlank(country.getCountryCode())) {
            errors.add(new InputError("countryCode", "Country code can't be empty"));
        }
        if (StringUtils.isAllBlank(country.getFullName())) {
            errors.add(new InputError("fullName", "Full name of the country can't be empty"));
        }
        return errors;
    }

    @Override
    public Country findCountryByCountryCodeIgnoreCase(String countryCode) {
        return countryRepository.findCountryByCountryCodeIgnoreCase(countryCode);
    }
}
