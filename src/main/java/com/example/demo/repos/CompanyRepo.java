package com.example.demo.repos;

import com.example.demo.entity.Company;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CompanyRepo extends CrudRepository<Company, Integer> {

    void deleteById(Integer Id);
    List<Company> findByName(String name);
    List<Company> findByAddress(String Address);

}
