package com.finbox.idea_collab_service.manager.impl;

import com.finbox.idea_collab_service.dto.enums.IdeaReactionAction;
import com.finbox.idea_collab_service.entity.*;
import com.finbox.idea_collab_service.exception.ResourceNotFoundException;
import com.finbox.idea_collab_service.manager.IdeaManager;
import com.finbox.idea_collab_service.repository.IdeaRepository;
import com.finbox.idea_collab_service.repository.IdeaVoteRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IdeaManagerImpl implements IdeaManager {

    private final IdeaRepository ideaRepository;


    /**
     * Constructor for IdeaManagerImpl.
     *
     * @param ideaRepository the repository to be used for idea management
     */

    private final IdeaVoteRepository ideaVoteRepository;

    private final EmployeeManagerImpl employeeManager;

    public IdeaManagerImpl(IdeaRepository ideaRepository, IdeaVoteRepository ideaVoteRepository, EmployeeManagerImpl employeeManager) {
        this.ideaRepository = ideaRepository;
        this.ideaVoteRepository = ideaVoteRepository;
        this.employeeManager = employeeManager;
    }

    @Override
    public Idea createIdea(Idea idea) {
        if (idea == null) {
//            TODO: add custom exception
            throw new IllegalArgumentException("Idea cannot be null");
        }
        ideaRepository.save(idea);
        return idea;
    }

    @Override
    public void updateIdea(String ideaId, String title, String description) {

    }

    @Override
    public void deleteIdea(String ideaId) {
        if (ideaId == null || ideaId.isEmpty()) {
            throw new IllegalArgumentException("Idea ID cannot be null or empty");
        }
        Idea idea = ideaRepository.findIdeaById(ideaId).orElseThrow(() -> new ResourceNotFoundException(
                String.format("Idea does not exist  %s", ideaId)));;


        idea.setStatus(IdeaStatus.INACTIVE);
        ideaRepository.save(idea);

    }

    @Override
    public void voteOnIdea(String ideaId, String employeeId, VoteStatus upvote) {
        Idea idea = ideaRepository.findIdeaById(ideaId).orElseThrow(() -> new ResourceNotFoundException(
                String.format("Idea does not exist  %s", ideaId)));

        if (idea.getStatus() == IdeaStatus.INACTIVE) {
//            TODO: add custom exception
            throw new IllegalArgumentException("Idea is inactive and cannot be voted on");
        }

        Employee employee;

        try {
             employee = employeeManager.getEmployeeById(employeeId);
        } catch (ResourceNotFoundException e) {
//            TODO: add custom exception
            throw new ResourceNotFoundException(
                    String.format("Employee does not exist  %s", employeeId));
        }

        IdeaVote ideaVote = new IdeaVote();
        ideaVote.setIdea(idea);
        ideaVote.setEmployee(employee);
        ideaVote.setVoteStatus(upvote);
        ideaVoteRepository.save(ideaVote);
    }

    @Override
    public void unvoteOnIdea(String ideaId, String employeeId) {

    }

    @Override
    public Idea getIdeaById(String ideaId) {
        return null;
    }

    @Override
    public List<Idea> getAllIdeas() {
        return null;
    }

    @Override
    public List<Idea> getIdeasByEmployeeId(String employeeId) {
        return null;
    }

    @Override
    public IdeaVote getIdeaVoteById(String ideaVoteId) {
        return ideaVoteRepository.findIdeaVoteById(ideaVoteId).orElseThrow(() -> new ResourceNotFoundException(
                String.format("IdeaVote does not exist for IdeaVote id = %s", ideaVoteId)));
    }
}
