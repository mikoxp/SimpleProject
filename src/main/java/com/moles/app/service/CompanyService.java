package com.moles.app.service;

import com.moles.app.entity.Company;
import com.moles.app.entity.QCompany;
import com.moles.app.repository.CompanyRepository;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    @PersistenceContext
    private EntityManager entityManager;


    public List<Company> byName(@PathVariable(name = "name") String name){
        QCompany company = QCompany.company;
        JPAQuery<Company> query = new JPAQuery<>(entityManager);
        return query.select(company)
                .from(company)
                .where(company.name.containsIgnoreCase(name))
                .fetch();
    }
}
