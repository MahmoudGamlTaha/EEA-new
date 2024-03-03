package com.backend.EEA.controllers.masterdata;

import com.backend.EEA.controllers.BaseRestController;
import com.backend.EEA.model.dto.masterdata.TaskDto;
import com.backend.EEA.model.dto.search.TaskSearchForm;
import com.backend.EEA.model.entity.masterdata.Task;
import com.backend.EEA.services.TaskService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basic-data/task")
public class TaskController
        extends BaseRestController<Task, TaskDto, TaskSearchForm> {

    public TaskController(TaskService taskservice) {
        super(taskservice);
    }
}
