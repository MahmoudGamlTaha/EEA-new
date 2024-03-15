package com.backend.EEA.mapper.masterdata;

import com.backend.EEA.business.dao.repositories.masterdat.CompanyRepository;
import com.backend.EEA.common.NumberToArabicText;
import com.backend.EEA.model.dto.masterdata.RequestFeesDto;
import com.backend.EEA.model.dto.masterdata.RequestFeesInvoiceDetailDto;
import com.backend.EEA.model.dto.masterdata.RequestFeesInvoiceDto;
import com.backend.EEA.model.entity.masterdata.Company;
import com.backend.EEA.model.entity.masterdata.RequestFees;
import com.backend.EEA.model.entity.masterdata.RequestHeader;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

@Mapper(componentModel = "spring", uses = {CurrencyRateMapper.class})
public interface RequestFeesMapper {
    RequestFees toEntity(RequestFeesDto requestFeesDto);

    RequestFeesDto toDto(RequestFees requestFees);
    default RequestFeesInvoiceDto toRequestFeesInvoiceDto(RequestFees requestFees, RequestHeader requestHeader, Company company){
        RequestFeesInvoiceDto requestFeesInvoiceDto = new RequestFeesInvoiceDto();
        requestFeesInvoiceDto.setInvoiceNumber(requestFees.getId().toString());
        requestFeesInvoiceDto.setRequesterName(requestHeader.getRequester().getName());
        requestFeesInvoiceDto.setCreatedDate(requestFees.getLastUpdateDate());
        requestFeesInvoiceDto.setEstablishCode(requestHeader.getEntity().getEstablishCode());
        requestFeesInvoiceDto.setCompanyName(company.getName());
        requestFeesInvoiceDto.setCompanyNumber(company.getCommercialNumber());
        Double totalFees = requestFees.getTotalFees();
        requestFeesInvoiceDto.setTotalFee(totalFees);
        RequestFeesInvoiceDetailDto requestFeesInvoiceDetailDto = new RequestFeesInvoiceDetailDto();
        List<RequestFeesInvoiceDetailDto> requestFeesInvoiceDetailDtos = new LinkedList<RequestFeesInvoiceDetailDto>();
        requestFeesInvoiceDetailDto.setAmount(totalFees);
        requestFeesInvoiceDetailDto.setService(requestHeader.getRequestType().getDescription());
        requestFeesInvoiceDetailDtos.add(requestFeesInvoiceDetailDto);

        requestFeesInvoiceDto.setFeesInArabic(NumberToArabicText.convertToArabicText(totalFees));
        requestFeesInvoiceDto.setRequestFeesInvoiceDetailDtoList(requestFeesInvoiceDetailDtos);
        return requestFeesInvoiceDto;
    }

}
