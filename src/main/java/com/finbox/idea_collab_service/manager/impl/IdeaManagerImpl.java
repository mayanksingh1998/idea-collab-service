package com.finbox.idea_collab_service.manager.impl;

import com.finbox.idea_collab_service.entity.*;
import com.finbox.idea_collab_service.exception.ResourceNotFoundException;
import com.finbox.idea_collab_service.exception.UserNotAllowedForVoteException;
import com.finbox.idea_collab_service.manager.IdeaManager;
import com.finbox.idea_collab_service.repository.IdeaRepository;
import com.finbox.idea_collab_service.repository.IdeaReactionRepository;
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

    private final IdeaReactionRepository ideaReactionRepository;

    private final EmployeeManagerImpl employeeManager;

    public IdeaManagerImpl(IdeaRepository ideaRepository, IdeaReactionRepository ideaReactionRepository, EmployeeManagerImpl employeeManager) {
        this.ideaRepository = ideaRepository;
        this.ideaReactionRepository = ideaReactionRepository;
        this.employeeManager = employeeManager;
    }

    @Override
    public Idea createOrUpdateIdea(Idea idea) {
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
            System.out.println("----------------++-----------------------");

            throw new ResourceNotFoundException(

            String.format("Employee does not exist  %s", employeeId));
        }
        if (idea.getCreatedBy().getId().equals(employee.getId())) {
            System.out.println("---------------------------------------");
            throw new UserNotAllowedForVoteException("User cannot vote on their own idea");

        }
        if (upvote == VoteStatus.UPVOTE){
            idea.setVotesCount(idea.getVotesCount() + 1);
            ideaRepository.save(idea);
        } else if (upvote == VoteStatus.DOWNVOTE) {
            idea.setVotesCount(idea.getVotesCount() - 1);
            ideaRepository.save(idea);
        } else {
            System.out.println("--------99999-------------------------------");
            throw new IllegalArgumentException("Invalid vote status");

        }

        IdeaReaction ideaReaction = new IdeaReaction();
        ideaReaction.setIdea(idea);
        ideaReaction.setEmployee(employee);
        ideaReaction.setVoteStatus(upvote);
        ideaReactionRepository.save(ideaReaction);
    }

    @Override
    public void unvoteOnIdea(String ideaId, String employeeId) {

    }

    @Override
    public Idea getIdeaById(String ideaId) {
        return ideaRepository.findIdeaById(ideaId).orElseThrow(() -> new ResourceNotFoundException(
                String.format("Idea does not exist for Idea id = %s", ideaId)));
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
    public IdeaReaction getIdeaVoteById(String ideaVoteId) {
        return ideaReactionRepository.findIdeaVoteById(ideaVoteId).orElseThrow(() -> new ResourceNotFoundException(
                String.format("IdeaVote does not exist for IdeaVote id = %s", ideaVoteId)));
    }

    @Override
    public List<IdeaReaction> getIdeaReactionsByIdeaId(String ideaId) {
        return ideaReactionRepository.findIdeaReactionsByIdeaId(ideaId);
    }
}
