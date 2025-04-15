package com.finbox.idea_collab_service.repository;

import com.finbox.idea_collab_service.entity.IdeaReaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IdeaReactionRepository extends JpaRepository<IdeaReaction, Integer> {
    IdeaReaction findIdeaVoteByIdeaId(String ideaId);
    IdeaReaction findIdeaVoteByEmployeeId(String employeeId);

    Optional<IdeaReaction> findIdeaVoteByIdeaIdAndEmployeeId(String ideaId, String employeeId);
    Optional<IdeaReaction> findIdeaVoteById(String ideaVoteId);

    List<IdeaReaction> findIdeaReactionsByIdeaId(String ideaId);
}
