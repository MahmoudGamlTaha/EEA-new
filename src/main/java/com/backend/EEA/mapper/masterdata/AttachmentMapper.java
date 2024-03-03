package com.backend.EEA.mapper.masterdata;

import com.backend.EEA.model.dto.masterdata.AttachmentDto;
import com.backend.EEA.model.dto.masterdata.RequestDetailDto;
import com.backend.EEA.model.entity.masterdata.Attachment;
import com.backend.EEA.model.entity.masterdata.RequestDetail;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface AttachmentMapper {

    Attachment toAttachment(AttachmentDto attachmentDto);

    AttachmentDto toAttachmentDto(Attachment attachment);
    @AfterMapping
    default public void calledWithSourceAndTarget(AttachmentDto anySource, @MappingTarget Attachment target) {
        target.setEntityId(1L);
    }
}
