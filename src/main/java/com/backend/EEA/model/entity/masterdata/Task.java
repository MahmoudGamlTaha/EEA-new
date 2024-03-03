package com.backend.EEA.model.entity.masterdata;

import com.backend.EEA.model.entity.BaseHeaderEntityGen;
import com.backend.EEA.model.entity.BaseHeaderEntityIntegrate;
import com.backend.EEA.model.enums.TaskStatusEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "task")
public class Task extends BaseHeaderEntityGen {

    @Column(name = "name")
    private String name;

    @JoinColumn(name = "assignee_user_id",insertable = false, updatable = false)
    @ManyToOne()
    private User assigneeUser;

    @Column(name = "assignee_user_id")
    private Long assigneeUserId;

    @Column(name = "detail")
    private String detail;

    @Column(name = "status")
    private TaskStatusEnum taskStatus;

    @OneToMany(mappedBy = "comment")
    List<Comment> comments;
}
