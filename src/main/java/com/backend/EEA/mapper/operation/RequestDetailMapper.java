package com.backend.EEA.mapper.operation;

import com.backend.EEA.mapper.masterdata.ActivityMapper;
import com.backend.EEA.mapper.masterdata.AttachmentMapper;
import com.backend.EEA.mapper.masterdata.CompanyTypeMapper;
import com.backend.EEA.model.dto.masterdata.RequestDetailDto;
import com.backend.EEA.model.dto.masterdata.RequestHeaderDto;
import com.backend.EEA.model.entity.masterdata.RequestDetail;
import com.backend.EEA.model.entity.masterdata.RequestHeader;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {AttachmentMapper.class, ActivityMapper.class})
public interface RequestDetailMapper {
   @Mapping(source = "companyActivity", target = "companyActivity")
   RequestDetail toRequestDetail(RequestDetailDto requestDetailDto);

   RequestDetailDto toRequestHeaderDto(RequestDetail requestHeader);
   @AfterMapping
   default public void calledWithSourceAndTarget(RequestDetailDto anySource, @MappingTarget RequestDetail target) {
      target.setEntityId(1L);
   }
}
