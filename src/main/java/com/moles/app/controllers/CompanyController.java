package com.moles.app.controllers;

import com.moles.app.dto.CompanyDto;
import com.moles.app.entity.Company;
import com.moles.app.repository.CompanyRepository;
import com.moles.app.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/company")
@AllArgsConstructor
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    private  final CompanyService companyService;

    @GetMapping("/all")
    public List<Company> getAll(){
        List<Company> all = companyRepository.findAll();
        return all;
    }

    @GetMapping("by/name")
    public List<CompanyDto> byName(@RequestParam("name") String name){
        return companyService.byName(name);
    }
}
