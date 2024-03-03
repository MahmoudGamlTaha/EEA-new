package com.backend.EEA.mapper.masterdata;

import com.backend.EEA.model.dto.masterdata.CountryDto;
import com.backend.EEA.model.dto.masterdata.RatingDto;
import com.backend.EEA.model.entity.masterdata.Country;
import com.backend.EEA.model.entity.masterdata.Rating;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RatingMapper {

    Rating toEntity(RatingDto ratingDto);

    RatingDto toDto(Rating rating);

}
