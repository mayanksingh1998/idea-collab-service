package com.finbox.idea_collab_service.service.impl;

import com.finbox.idea_collab_service.dto.reponse.AllIdeasResponseDto;
import com.finbox.idea_collab_service.dto.reponse.IdeaReactionsResponseDto;
import com.finbox.idea_collab_service.dto.reponse.IdeaResponse;
import com.finbox.idea_collab_service.dto.reponse.MetaDataResponse;
import com.finbox.idea_collab_service.dto.request.CreateIdeaRequest;
import com.finbox.idea_collab_service.dto.request.IdeaFilterRequest;
import com.finbox.idea_collab_service.dto.request.IdeaReactionRequestDto;
import com.finbox.idea_collab_service.entity.*;
import com.finbox.idea_collab_service.exception.InvalidIdeaReactionException;
import com.finbox.idea_collab_service.exception.UserNotAllowedForVoteException;
import com.finbox.idea_collab_service.exception.UserNotAuthorizedException;
import com.finbox.idea_collab_service.helper.IdeaServiceHelper;
import com.finbox.idea_collab_service.manager.EmployeeManager;
import com.finbox.idea_collab_service.manager.IdeaManager;
import com.finbox.idea_collab_service.mapper.IdeaMapper;
import com.finbox.idea_collab_service.repository.IdeaRepository;
import com.finbox.idea_collab_service.repository.TagRepository;
import com.finbox.idea_collab_service.service.IdeaService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class IdeaServiceImpl implements IdeaService {

    private final IdeaManager ideaManager;
     private final IdeaMapper ideaMapper;

     private final EmployeeManager employeeManager;

     private final IdeaServiceHelper ideaServiceHelper;


    private final TagRepository tagRepository;
    private final IdeaRepository ideaRepository;

    public IdeaServiceImpl(IdeaManager ideaManager, IdeaMapper ideaMapper, EmployeeManager employeeManager, IdeaServiceHelper ideaServiceHelper, TagRepository tagRepository, IdeaRepository ideaRepository) {
        this.ideaManager = ideaManager;
        this.ideaMapper = ideaMapper;
        this.employeeManager = employeeManager;
        this.ideaServiceHelper = ideaServiceHelper;
        this.tagRepository = tagRepository;
        this.ideaRepository = ideaRepository;
    }


    @Override
    public Idea createIdea(CreateIdeaRequest createIdeaRequest, String employeeId) {
        Employee employee = employeeManager.getEmployeeById(employeeId);

        List<Tag> tags = tagRepository.findAllByIdIn(createIdeaRequest.getTagIds());
        if (tags.size() != createIdeaRequest.getTagIds().size()) {
            throw new RuntimeException("Some tags not found");
        }

        Idea idea = ideaMapper.toIdeaDto(createIdeaRequest, employee);
        idea.getTags().addAll(tags);

        return ideaManager.createOrUpdateIdea(idea);
    }

    @Override
    public IdeaResponse getIdeaById(String ideaId) {
        Idea idea = ideaManager.getIdeaById(ideaId);
        return IdeaResponse.builder().idea(idea).build();
    }

    @Override
    public MetaDataResponse getMetaData(String employeeId) {
        List<Tag> tags = ideaServiceHelper.getTags();
        return MetaDataResponse.builder().tags(tags).build();
    }

    @Override
    public AllIdeasResponseDto getFilteredIdeas(IdeaFilterRequest request) {
        // Convert sort string into Sort object
        Sort.Direction direction = Sort.Direction.fromString(request.getOrder()); // "asc" or "desc"
        Sort sort = Sort.by(direction, request.getSortBy()); // "votesCount", "createdAt", etc.

        System.out.println(request.getOrder());
        System.out.println(request.getSortBy());
        System.out.println("request.getStartDate()");
        Timestamp defaultStart = Timestamp.valueOf("1900-01-01 00:00:00");
        Timestamp defaultEnd = Timestamp.valueOf("2999-12-31 23:59:59");

        Timestamp startDate = (request.getStartDate() != null) ? request.getStartDate() : defaultStart;
        Timestamp endDate = (request.getEndDate() != null) ? request.getEndDate() : defaultEnd;

        // Call the repo method
        List<Idea> ideas =  ideaRepository.findFilteredIdeas(
                request.getEmployeeIds(),
                request.getStatuses(),
                request.getTags(),
                startDate,
                endDate,
                sort
        );
        return ideaMapper.toAllIdeasResponseDto(ideas);

    }

    @Override
    public IdeaReactionsResponseDto getIdeaReactionsById(String ideaId) {
        List<IdeaReaction> ideaReactions = ideaManager.getIdeaReactionsByIdeaId(ideaId);

        return ideaMapper.toIdeaReactionsResponseDto(ideaReactions);
    }


//    @Override
//    public Idea reactOnIdea(String ideaId, String employeeId, IdeaReactionAction reaction) {
//        Idea idea = ideaManager.getIdeaById(ideaId);
//        Employee employee = employeeManager.getEmployeeById(employeeId);
////        idea.setReaction(reaction);
//    }


    @Override
    public Boolean reactOnIdea(String ideaId, IdeaReactionRequestDto ideaReactionRequestDto, String employeeId) {
        try {
            ideaManager.reactOnIdea(ideaId, employeeId, ideaServiceHelper.getAndValidateVoteStatus(ideaReactionRequestDto.getAction()));
            return true;
        }catch (UserNotAllowedForVoteException e){
            throw new UserNotAllowedForVoteException(e.getMessage());
        } catch (InvalidIdeaReactionException e){
            throw new InvalidIdeaReactionException(e.getMessage());
        } catch (UserNotAuthorizedException e){
            throw new UserNotAuthorizedException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Boolean postIdea(String ideaId, String employeeId) {
        Idea idea = ideaManager.getIdeaById(ideaId);
        if (!idea.getCreatedBy().getId().equals(employeeId)) {
//            TODO: custom
            throw new UserNotAllowedForVoteException("You are not allowed to post this idea");
        }
        idea.setStatus(IdeaStatus.ACTIVE);
        ideaManager.createOrUpdateIdea(idea);
        return true;
    }

    @Override
    public List<Idea> getIdeasByEmployeeId(String employeeId) {
        return ideaManager.getIdeasByEmployeeId(employeeId);
    }

}
