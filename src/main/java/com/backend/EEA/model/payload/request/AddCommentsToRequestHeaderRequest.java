package com.backend.EEA.model.payload.request;

import com.backend.EEA.model.dto.masterdata.CommentsDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddCommentsToRequestHeaderRequest {

        private List<CommentsDto> commentsList;
}
