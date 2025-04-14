package com.finbox.idea_collab_service.service.impl;

import com.finbox.idea_collab_service.dto.reponse.EmployeeIdeasDto;
import com.finbox.idea_collab_service.dto.reponse.IdeaResponseDto;
import com.finbox.idea_collab_service.dto.request.CreateIdeaRequest;
import com.finbox.idea_collab_service.dto.request.IdeaReactionRequestDto;
import com.finbox.idea_collab_service.entity.Employee;
import com.finbox.idea_collab_service.entity.Idea;
import com.finbox.idea_collab_service.helper.IdeaServiceHelper;
import com.finbox.idea_collab_service.manager.EmployeeManager;
import com.finbox.idea_collab_service.manager.IdeaCollabManager;
import com.finbox.idea_collab_service.manager.IdeaManager;
import com.finbox.idea_collab_service.mapper.CollabRequestMapper;
import com.finbox.idea_collab_service.mapper.IdeaMapper;
import com.finbox.idea_collab_service.service.IdeaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdeaServiceImpl implements IdeaService {

    private final IdeaManager ideaManager;
     private final IdeaMapper ideaMapper;

     private final EmployeeManager employeeManager;

     private final IdeaServiceHelper ideaServiceHelper;

     private final CollabRequestMapper collabRequestMapper;

    private final IdeaCollabManager ideaCollabManager;

    public IdeaServiceImpl(IdeaManager ideaManager, IdeaMapper ideaMapper, EmployeeManager employeeManager, IdeaServiceHelper ideaServiceHelper, CollabRequestMapper collabRequestMapper, IdeaCollabManager ideaCollabManager) {
        this.ideaManager = ideaManager;
        this.ideaMapper = ideaMapper;
        this.employeeManager = employeeManager;
        this.ideaServiceHelper = ideaServiceHelper;
        this.collabRequestMapper = collabRequestMapper;
        this.ideaCollabManager = ideaCollabManager;
    }


    @Override
    public Idea createIdea(CreateIdeaRequest createIdeaRequest) {
        Employee employee = employeeManager.getEmployeeById(createIdeaRequest.getEmployeeId());
        if (employee == null) {
            // TODO: Handle the case where the employee is not found
            throw new RuntimeException("Employee not found");
        }
        Idea idea = ideaMapper.toIdeaDto(createIdeaRequest, employee);
        return ideaManager.createIdea(idea);
    }

    @Override
    public IdeaResponseDto getIdeaById(String ideaId) {
        Idea idea = ideaManager.getIdeaById(ideaId);
        return IdeaResponseDto.builder().idea(idea).build();
    }

    @Override
    public Idea getIdeaByTitle(String title) {
        return null;
    }

    @Override
    public Idea updateIdeaStatus(String ideaId, CreateIdeaRequest createIdeaRequest) {
        return null;
    }

//    @Override
//    public Idea reactOnIdea(String ideaId, String employeeId, IdeaReactionAction reaction) {
//        Idea idea = ideaManager.getIdeaById(ideaId);
//        Employee employee = employeeManager.getEmployeeById(employeeId);
////        idea.setReaction(reaction);
//    }

    @Override
    public void deleteIdea(String ideaId) {

    }

    @Override
    public Boolean reactOnIdea(String ideaId, IdeaReactionRequestDto ideaReactionRequestDto) {
        try {
            ideaManager.voteOnIdea(ideaId, ideaReactionRequestDto.getEmployeeId(), ideaServiceHelper.getAndValidateVoteStatus(ideaReactionRequestDto.getAction()));
            return true;
        }catch (Exception e){
            // TODO: Handle the case where the employee is not found
            return false;
        }
    }

    @Override
    public Idea unvoteIdea(String ideaId, String employeeId) {
        return null;
    }

    @Override
    public List<IdeaResponseDto> getIdeasByEmployeeId(String employeeId) {
        List<Idea> ideas =  ideaManager.getIdeasByEmployeeId(employeeId);
        return ideas.stream()
                .map(idea -> IdeaResponseDto.builder().idea(idea).build())
                .toList();
    }

    @Override
    public Idea getIdeaByCollaborationRequestId(String requestId) {
        return null;
    }


}
