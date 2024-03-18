package com.backend.EEA.model.dto.masterdata;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestToChangeHarborDto {


    @NotNull(message = "attachment can not be null")
    @NotEmpty(message = "List must not be empty")
    List<AttachmentDto> otherAttachment;

    @NotNull(message = "attachment can not be null")
    @NotEmpty(message = "List must not be empty")
    List<Long> harborIds;

    private Integer quantitiesToBeCompleted;

    private Integer exportedQuantities;

    private String reasonsForNotCompletingTheQuantity;
}
