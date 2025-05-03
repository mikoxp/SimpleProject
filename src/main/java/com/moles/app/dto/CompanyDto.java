package com.moles.app.dto;

import java.util.List;

public record CompanyDto(String name, List<CompanyDto> subCompany) {
}
