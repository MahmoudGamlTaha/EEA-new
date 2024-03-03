package com.backend.EEA.model.entity.masterdata;

import com.backend.EEA.model.entity.BaseHeaderEntityGen;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Table(name = "comments")
@Data
@Entity
public class Comment extends BaseHeaderEntityGen {
    @Column(name = "comment")
    private String comment;

    @OneToMany(mappedBy = "comment")
    private List<Attachment> attachments;
}
