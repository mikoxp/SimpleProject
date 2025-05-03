package com.moles.app.service;

import com.moles.app.dto.CompanyDto;
import com.moles.app.entity.Company;
import com.moles.app.entity.QCompany;
import com.moles.app.mappers.CompanyMapper;
import com.moles.app.repository.CompanyRepository;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @PersistenceContext
    private EntityManager entityManager;


    public List<CompanyDto> byName(@PathVariable(name = "name") String name){
        QCompany company = QCompany.company;
        JPAQuery<Company> query = new JPAQuery<>(entityManager);
        List<Company> fetch = query.select(company)
                .from(company)
                .where(company.name.containsIgnoreCase(name))
                .fetch();
        return fetch.stream().map(companyMapper::toDto).collect(Collectors.toList());
    }
}
