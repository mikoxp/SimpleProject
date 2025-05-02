package com.moles.app.controllers;

import com.moles.app.entity.Company;
import com.moles.app.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;
    @GetMapping("/all")
    public List<Company> getAll(){
        List<Company> all = companyRepository.findAll();
        return all;
    }
}
