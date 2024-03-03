package com.backend.EEA.business.dao.repositories.masterdat;

import com.backend.EEA.business.dao.common.GenericJPARepository;
import com.backend.EEA.model.entity.masterdata.Attachment;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepository extends GenericJPARepository<Attachment> {
}
