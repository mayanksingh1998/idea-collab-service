package com.finbox.idea_collab_service.helper;

import com.finbox.idea_collab_service.dto.enums.IdeaReactionAction;
import com.finbox.idea_collab_service.entity.Tag;
import com.finbox.idea_collab_service.entity.VoteStatus;
import com.finbox.idea_collab_service.exception.InvalidIdeaReactionException;
import com.finbox.idea_collab_service.repository.TagRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IdeaServiceHelper {

    private TagRepository tagRepository;

    public VoteStatus getAndValidateVoteStatus(IdeaReactionAction action) {
        return switch (action) {
            case UP_VOTE -> VoteStatus.UPVOTE;
            case DOWN_VOTE -> VoteStatus.DOWNVOTE;
            case NEUTRAL_VOTE -> VoteStatus.NEUTRAL;
            default -> throw new InvalidIdeaReactionException("Invalid idea reaction: " + action);
        };
    }

    public List<Tag> getTags() {
        return tagRepository.findAll();
    }
}
