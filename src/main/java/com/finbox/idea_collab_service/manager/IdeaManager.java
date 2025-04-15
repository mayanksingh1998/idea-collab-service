package com.finbox.idea_collab_service.manager;

import com.finbox.idea_collab_service.entity.Idea;
import com.finbox.idea_collab_service.entity.IdeaReaction;
import com.finbox.idea_collab_service.entity.VoteStatus;

import java.util.List;

public interface IdeaManager {

    Idea createOrUpdateIdea(Idea idea);



    void reactOnIdea(String ideaId, String employeeId, VoteStatus upvote);

    Idea getIdeaById(String ideaId);


    List<Idea> getIdeasByEmployeeId(String employeeId);


    List<IdeaReaction> getIdeaReactionsByIdeaId(String ideaId);
}
