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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @PersistenceContext
    private EntityManager entityManager;

    private List<CompanyDto> simpleMapping(List<Company> fetch) {
        return fetch.stream().map(companyMapper::toDto).collect(Collectors.toList());
    }

    /**
     * @return all company
     */
    public List<CompanyDto> getAll() {
        log.info("Get all company");
        return simpleMapping(companyRepository.findAll());
    }

    /**
     * @param name name
     * @return company by name
     */
    public List<CompanyDto> byName(@PathVariable(name = "name") String name) {
        log.info("Get company by name {}",name);
        QCompany company = new QCompany("main");
        QCompany subcompany = new QCompany("sub");
        JPAQuery<Company> query = new JPAQuery<>(entityManager);
        List<Company> fetch = query.
                select(company)
                .from(company)
                .leftJoin(company.subCompany, subcompany)
                .where(company.name.containsIgnoreCase(name)
                        .or(subcompany.name.containsIgnoreCase(name))).fetch();
        return simpleMapping(fetch);
    }


}
