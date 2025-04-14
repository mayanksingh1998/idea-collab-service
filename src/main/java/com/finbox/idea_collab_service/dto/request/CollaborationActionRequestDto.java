package com.finbox.idea_collab_service.dto.request;

import com.finbox.idea_collab_service.dto.enums.CollaborationRequestAction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CollaborationActionRequestDto {
    private CollaborationRequestAction action;

}
