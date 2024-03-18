package com.backend.EEA.mapper.operation;

import com.backend.EEA.business.dao.repositories.masterdat.CoalTypeRepository;
import com.backend.EEA.mapper.masterdata.*;
import com.backend.EEA.model.dto.masterdata.CountryDto;
import com.backend.EEA.model.dto.masterdata.HarborDto;
import com.backend.EEA.model.dto.masterdata.RequestHeaderDto;
import com.backend.EEA.model.dto.masterdata.RequestHeaderModelDto;
import com.backend.EEA.model.entity.masterdata.*;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

@Mapper(componentModel = "spring", uses = {RequestDetailMapper.class, AttachmentMapper.class, CoalTypeMapper.class, RequestTypeMapper.class, UnloadWayMapper.class, AdministrativeStructureMapper.class})
public interface RequestHeaderMapper {
   @Mapping(source = "requestTypeId",target = "type")
   @Mapping(source = "notes", target = "description")
   @Mapping(source = "unLoadWayId", target = "unloadWayId")
   RequestHeader toRequestHeader(RequestHeaderDto requestHeaderDto);
   @Mapping(source = "type", target = "requestTypeId")
   @Mapping(source = "description", target = "notes")
   @Mapping(source = "administrativeForwardTo", target = "administrativeForwardTo")
   @Mapping(source = "unloadWayId", target = "unLoadWayId")
   RequestHeaderDto toRequestHeaderDto(RequestHeader requestHeader);
    @Mapping(source = "requestType.name", target = "name")
   default RequestHeaderModelDto toRequestModel(RequestHeader requestHeader, CoalType coalType, UnloadWay unloadWay){
       if ( requestHeader == null ) {
          return null;
       }

       RequestHeaderModelDto requestHeaderModelDto = new RequestHeaderModelDto();

       requestHeaderModelDto.setName( requestHeader.getRequestType().getName() );
       requestHeaderModelDto.setShipDate( requestHeader.getShipDate() );
       requestHeaderModelDto.setImportCoalCompany( requestHeader.getImportCoalCompany() );
       requestHeaderModelDto.setShipName( requestHeader.getShipName() );
       requestHeaderModelDto.setArrivedDate( requestHeader.getArrivedDate() );
       requestHeaderModelDto.setLandingHarbor( harborToHarborDto( requestHeader.getLandingHarbor() ) );
       requestHeaderModelDto.setWeightInTon( requestHeader.getWeightInTon() );
       requestHeaderModelDto.setCoalTypeId( requestHeader.getCoalTypeId() );
       requestHeaderModelDto.setShipmentStages( requestHeader.getShipmentStages() );
       requestHeaderModelDto.setUnloadWayId( requestHeader.getUnloadWayId() );
       requestHeaderModelDto.setImportHarbor( harborToHarborDto( requestHeader.getImportHarbor() ) );
       requestHeaderModelDto.setCompanyId( requestHeader.getCompanyId() );
       requestHeaderModelDto.setCoalType(coalType.getName());
       requestHeaderModelDto.setUnloadWayName(unloadWay.getName());
       requestHeaderModelDto.setRequestId(requestHeader.getId());
       return requestHeaderModelDto;
    }
   default HarborDto harborToHarborDto(Harbor harbor) {
      if ( harbor == null ) {
         return null;
      }

      HarborDto harborDto = new HarborDto();

      harborDto.setId( harbor.getId() );
      harborDto.setCode( harbor.getCode() );
      harborDto.setHarborType( harbor.getHarborType() );
      harborDto.setName( harbor.getName() );
      harborDto.setCountry( countryDtoToCountry( harbor.getCountry() ) );
      harborDto.setCountryId( harbor.getCountryId() );

      return harborDto;
   }
   default CountryDto countryDtoToCountry(Country countryDto) {
      if ( countryDto == null ) {
         return null;
      }

      CountryDto country = new CountryDto();

      country.setId( countryDto.getId() );
      country.setCode( countryDto.getCode() );
      country.setName( countryDto.getName() );

      return country;
   }
   @AfterMapping
   default public void calledWithSourceAndTarget(RequestHeaderDto anySource, @MappingTarget RequestHeader target) {
      target.setEntityId(1L);
   }

}
