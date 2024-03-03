package com.backend.EEA.mapper.masterdata;

import com.backend.EEA.model.dto.masterdata.AdministrativeStructureDto;
import com.backend.EEA.model.dto.masterdata.TaskDto;
import com.backend.EEA.model.entity.masterdata.AdministrativeStructure;
import com.backend.EEA.model.entity.masterdata.Task;
import lombok.Data;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    Task toTaskEntity(TaskDto dto);

    TaskDto toTaskDto(Task entity);
}
