package com.finbox.idea_collab_service.dto.request;


import com.finbox.idea_collab_service.dto.enums.IdeaReactionAction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class IdeaReactionRequestDto {
    private IdeaReactionAction action;
    private String employeeId;
}
