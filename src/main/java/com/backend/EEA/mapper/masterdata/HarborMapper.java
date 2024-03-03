package com.backend.EEA.mapper.masterdata;

import com.backend.EEA.model.dto.masterdata.HarborDto;
import com.backend.EEA.model.entity.masterdata.Harbor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CountryMapper.class})
public interface HarborMapper {

    Harbor toHarbor(HarborDto harborDto);

    HarborDto toHarborDto(Harbor permission);

}
