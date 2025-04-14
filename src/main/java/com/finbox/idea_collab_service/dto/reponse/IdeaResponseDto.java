package com.finbox.idea_collab_service.dto.reponse;


import com.finbox.idea_collab_service.entity.Idea;
import com.finbox.idea_collab_service.entity.IdeaVote;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class IdeaResponseDto {
    private Idea idea;
    private List<IdeaVote> ideaVotes;
}
