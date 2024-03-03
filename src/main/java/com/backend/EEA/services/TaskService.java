package com.backend.EEA.services;

import com.backend.EEA.business.dao.repositories.masterdat.AdministrativeStructureRepository;
import com.backend.EEA.business.dao.repositories.masterdat.TaskRepository;
import com.backend.EEA.business.dao.specifications.masterdata.AdministrativeStructureSpecifications;
import com.backend.EEA.business.dao.specifications.masterdata.TaskStructureSpecifications;
import com.backend.EEA.exceptions.BusinessException;
import com.backend.EEA.mapper.masterdata.AdministrativeStructureMapper;
import com.backend.EEA.mapper.masterdata.TaskMapper;
import com.backend.EEA.model.dto.masterdata.AdministrativeStructureDto;
import com.backend.EEA.model.dto.masterdata.TaskDto;
import com.backend.EEA.model.dto.search.AdministrativeStructureSearchForm;
import com.backend.EEA.model.dto.search.TaskSearchForm;
import com.backend.EEA.model.entity.masterdata.AdministrativeStructure;
import com.backend.EEA.model.entity.masterdata.Task;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService extends BaseService<Task, TaskDto, TaskSearchForm> {

    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;


    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        super(taskRepository);
        this.taskMapper = taskMapper;
        this.taskRepository = taskRepository;
    }

    @Override
    protected Specification<Task> buildSpecification(TaskSearchForm searchParams) {
        return TaskStructureSpecifications.buildSpecifications(searchParams);
    }

    @Override
    protected List<TaskDto> mapDataListToDtoList(List<Task> list) {
        return list.stream().map(taskMapper::toTaskDto).collect(Collectors.toList());
    }

    @Override
    protected TaskDto prepareEntityToDto(Task object) {
        return taskMapper.toTaskDto(object);
    }

    @Override
    protected Task prepareDtoToEntity(TaskDto object) {
        return taskMapper.toTaskEntity(object);
    }

    @Override
    protected void setIdBeforeUpdate(long id, Task object) {
        object.setId(id);
    }

    @Override
    public TaskSearchForm prepareSearchPojo(String searchQuery) throws JsonProcessingException {
        return objectMapper.readValue(searchQuery, TaskSearchForm.class);
    }

    /* in case add root node, check that the root node already exists */
    @Override
    protected void doBeforeCreate(Task entity) throws BusinessException {

    }

    @Override
    protected void doBeforeEdit(Task entity) throws BusinessException {

    }

    @Override
    protected void doBeforeDelete(Long id) throws BusinessException {

    }


    private void validateName(AdministrativeStructure entity) {
    }
}
