package com.moles.app.mappers;

import com.moles.app.dto.CompanyDto;
import com.moles.app.entity.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    CompanyDto toDto(Company company);
}
