package com.finbox.idea_collab_service.repository;

import com.finbox.idea_collab_service.entity.IdeaVote;
import com.finbox.idea_collab_service.service.IdeaService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IdeaVoteRepository extends JpaRepository<IdeaVote, Integer> {
    IdeaVote findIdeaVoteByIdeaId(String ideaId);
    IdeaVote findIdeaVoteByEmployeeId(String employeeId);

    Optional<IdeaVote> findIdeaVoteByIdeaIdAndEmployeeId(String ideaId, String employeeId);
    Optional<IdeaVote> findIdeaVoteById(String ideaVoteId);
}
