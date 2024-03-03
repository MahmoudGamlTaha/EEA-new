package com.backend.EEA.services;


import com.backend.EEA.business.dao.repositories.masterdat.LoggerRepository;
import com.backend.EEA.mapper.masterdata.LoggerMapper;
import com.backend.EEA.model.dto.masterdata.LoggerDto;
import com.backend.EEA.model.dto.search.LoggerSearchForm;
import com.backend.EEA.model.entity.operation.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoggerService extends BaseService<Logger, LoggerDto, LoggerSearchForm> {

    LoggerRepository loggerRepository;
    LoggerMapper loggerMapper;
    public LoggerService( LoggerRepository loggerRepository, LoggerMapper loggerMapper) {
        super(loggerRepository);
        this.loggerMapper = loggerMapper;
        this.loggerRepository = loggerRepository;
    }

    @Override
    protected Specification<Logger> buildSpecification(LoggerSearchForm searchParams) {
        return null;
    }

    @Override
    protected List<LoggerDto> mapDataListToDtoList(List<Logger> list) {
        return list.stream().map(loggerMapper::toLoggerDto).collect(Collectors.toList());
    }

    @Override
    protected LoggerDto prepareEntityToDto(Logger object) {
        return loggerMapper.toLoggerDto(object);
    }

    @Override
    protected Logger prepareDtoToEntity(LoggerDto object) {
        return loggerMapper.toLogger(object);
    }

    @Override
    protected void setIdBeforeUpdate(long id, Logger object) {
          object.setId(id);
    }
    public List<LoggerDto> findLogsByRequestId(Long requestId){
        List<Logger> loggers = this.loggerRepository.findByTableAndRowId("request_header",requestId);
        return this.mapDataListToDtoList(loggers);
    }
    @Override
    public LoggerSearchForm prepareSearchPojo(String searchQuery) throws JsonProcessingException {
        return objectMapper.readValue(searchQuery, LoggerSearchForm.class);
    }
}
