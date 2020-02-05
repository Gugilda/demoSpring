package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface CompanyRepository extends CrudRepository<Company, Integer> {

    void deleteById(Integer Id);
    List<Company> findByName(String name);
    List<Company> findByAddress(String Address);

}
