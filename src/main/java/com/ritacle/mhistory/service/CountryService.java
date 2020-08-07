package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.Country;
import com.ritacle.mhistory.persistence.model.Response;

public interface CountryService {

    Response<Country> save(Country country);

    Country findCountryByCountryCodeIgnoreCase(String countryCode);
}
