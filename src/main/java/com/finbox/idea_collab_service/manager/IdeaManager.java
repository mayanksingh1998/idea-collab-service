package com.finbox.idea_collab_service.manager;

import com.finbox.idea_collab_service.entity.Idea;
import com.finbox.idea_collab_service.entity.IdeaVote;
import com.finbox.idea_collab_service.entity.VoteStatus;

import java.util.List;

public interface IdeaManager {

    Idea createIdea(Idea idea);

    void updateIdea(String ideaId, String title, String description);

    void deleteIdea(String ideaId);

    void voteOnIdea(String ideaId, String employeeId, VoteStatus upvote);

    void unvoteOnIdea(String ideaId, String employeeId);

    Idea getIdeaById(String ideaId);

    List<Idea> getAllIdeas();

    List<Idea> getIdeasByEmployeeId(String employeeId);

    IdeaVote getIdeaVoteById(String ideaVoteId);
}
