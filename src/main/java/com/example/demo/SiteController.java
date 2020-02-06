package com.example.demo;


import net.bytebuddy.description.field.FieldList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.*;


@org.springframework.stereotype.Controller

public class SiteController {
    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping()
    public String index(Model model) {
        Iterable<Company> companies = companyRepository.findAll();
        model.addAttribute("companies", companies);
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        companyRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/search")
    public String searchCompany(@RequestParam(value = "query", required = false) String query, @RequestParam(value = "field", required = false) String field, Model model) {

        List<Company> result = null;
        if ("name".equals(field))
            result = companyRepository.findByName(query);
        else if ("address".equals(field)) {
            result = companyRepository.findByAddress(query);
        }
        model.addAttribute("search", result);
        return "search";
    }

    @PostMapping("/company")
    public String checkCompany(@Valid Company company, BindingResult bindingResult, @RequestParam() String name, @RequestParam() String address, Model model) {

        if (bindingResult.hasErrors()) {
            List<FieldError> errorList = bindingResult.getFieldErrors();
            model.addAttribute("errors", errorList);
            return "company";
        }

        //сделать проверку на дубль
        if (companyRepository.findByName(name).size() != 0) {
            return "company";
        }

        company = new Company(name, address);
        companyRepository.save(company);

        return "success";
    }

    @GetMapping("/company")
    public String sendForm(Company company) {
        return "company";
    }


}