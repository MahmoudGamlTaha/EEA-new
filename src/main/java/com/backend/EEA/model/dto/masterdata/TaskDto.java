package com.backend.EEA.model.dto.masterdata;

import com.backend.EEA.model.entity.masterdata.Comment;
import com.backend.EEA.model.entity.masterdata.User;
import com.backend.EEA.model.enums.TaskStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    private String name;

    private User assigneeUser;

    private Long assigneeUserId;

    private String detail;

    private TaskStatusEnum taskStatus;

    List<Comment> comments;
}
