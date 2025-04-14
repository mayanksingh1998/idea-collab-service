package com.finbox.idea_collab_service.helper;

import com.finbox.idea_collab_service.dto.enums.IdeaReactionAction;
import com.finbox.idea_collab_service.entity.VoteStatus;
import org.springframework.stereotype.Component;

@Component
public class IdeaServiceHelper {

    public VoteStatus getAndValidateVoteStatus(IdeaReactionAction action) {
        return switch (action) {
            case UP_VOTE -> VoteStatus.UPVOTE;
            case DOWN_VOTE -> VoteStatus.DOWNVOTE;
            case NEUTRAL_VOTE -> VoteStatus.NEUTRAL;
//            TODO: add custom exception
            default -> throw new IllegalArgumentException("Invalid vote status: " + action);
        };
    }
}
