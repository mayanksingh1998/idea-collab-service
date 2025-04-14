package com.finbox.idea_collab_service.dto.reponse;

import com.finbox.idea_collab_service.entity.CollaborationRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class IdeaColabResponse {
    private CollaborationRequest collaborationRequest;
}
