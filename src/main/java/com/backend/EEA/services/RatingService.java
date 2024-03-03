package com.backend.EEA.services;

import com.backend.EEA.business.dao.repositories.masterdat.ComplainRepository;
import com.backend.EEA.business.dao.repositories.masterdat.RatingRepository;
import com.backend.EEA.exceptions.BusinessException;
import com.backend.EEA.mapper.masterdata.ComplainMapper;
import com.backend.EEA.mapper.masterdata.RatingMapper;
import com.backend.EEA.model.dto.masterdata.ComplainsDto;
import com.backend.EEA.model.dto.masterdata.RatingDto;
import com.backend.EEA.model.dto.search.ComplainsSearchForm;
import com.backend.EEA.model.dto.search.RatingSearchForm;
import com.backend.EEA.model.entity.masterdata.Complains;
import com.backend.EEA.model.entity.masterdata.Rating;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingService extends BaseService<Rating, RatingDto, RatingSearchForm> {

    RatingRepository ratingRepository;
    RatingMapper ratingMapper;
    public RatingService(RatingRepository ratingRepository, RatingMapper complainMapper) {
        super(ratingRepository);
        this.ratingMapper = complainMapper;
        this.ratingRepository = ratingRepository;
    }

    @Override
    protected Specification<Rating> buildSpecification(RatingSearchForm searchParams) {
        return null;
    }

    @Override
    protected List<RatingDto> mapDataListToDtoList(List<Rating> list) {
        return list.stream().map(ratingMapper::toDto).collect(Collectors.toList());
    }

    @Override
    protected RatingDto prepareEntityToDto(Rating object) {
        return ratingMapper.toDto(object);
    }

    @Override
    protected Rating prepareDtoToEntity(RatingDto object) {
        return ratingMapper.toEntity(object);
    }
    protected void doBeforeCreate(Rating complains){

    }
    @Override
    protected void setIdBeforeUpdate(long id, Rating object) {
          object.setId(id);
    }

    @Override
    public RatingSearchForm prepareSearchPojo(String searchQuery) throws JsonProcessingException {
        return objectMapper.readValue(searchQuery, RatingSearchForm.class);
    }
}
