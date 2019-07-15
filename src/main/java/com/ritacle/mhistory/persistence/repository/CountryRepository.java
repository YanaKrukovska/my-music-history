package com.ritacle.mhistory.persistence.repository;

import com.ritacle.mhistory.persistence.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,Long> {
}


