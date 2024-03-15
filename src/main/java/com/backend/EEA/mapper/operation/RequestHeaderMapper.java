package com.backend.EEA.mapper.operation;

import com.backend.EEA.mapper.masterdata.*;
import com.backend.EEA.model.dto.masterdata.RequestHeaderDto;
import com.backend.EEA.model.dto.masterdata.RequestHeaderModelDto;
import com.backend.EEA.model.entity.masterdata.RequestHeader;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
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

   RequestHeaderModelDto toRequestModel(RequestHeader requestHeader);

   @AfterMapping
   default public void calledWithSourceAndTarget(RequestHeaderDto anySource, @MappingTarget RequestHeader target) {
      target.setEntityId(1L);
   }

}
