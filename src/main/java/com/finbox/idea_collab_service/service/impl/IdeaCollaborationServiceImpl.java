package com.finbox.idea_collab_service.service.impl;

import com.finbox.idea_collab_service.dto.reponse.IdeaColabResponse;
import com.finbox.idea_collab_service.dto.request.CollabRequestDto;
import com.finbox.idea_collab_service.dto.request.CollaborationActionRequestDto;
import com.finbox.idea_collab_service.entity.CollaborationRequest;
import com.finbox.idea_collab_service.entity.Employee;
import com.finbox.idea_collab_service.entity.Idea;
import com.finbox.idea_collab_service.helper.IdeaCollaborationServiceHelper;
import com.finbox.idea_collab_service.manager.EmployeeManager;
import com.finbox.idea_collab_service.manager.IdeaCollabManager;
import com.finbox.idea_collab_service.manager.IdeaManager;
import com.finbox.idea_collab_service.mapper.CollabRequestMapper;
import com.finbox.idea_collab_service.service.IdeaCollaborationService;
import org.springframework.stereotype.Service;

@Service
public class IdeaCollaborationServiceImpl implements IdeaCollaborationService {
    private final IdeaCollabManager ideaCollabManager;
    private final IdeaManager ideaManager;
    private final EmployeeManager employeeManager;
    private final CollabRequestMapper collabRequestMapper;

    private final IdeaCollaborationServiceHelper ideaCollaborationServiceHelper;

    public IdeaCollaborationServiceImpl(IdeaCollabManager ideaCollabManager, IdeaManager ideaManager, EmployeeManager employeeManager, CollabRequestMapper collabRequestMapper, IdeaCollaborationServiceHelper ideaCollaborationServiceHelper) {
        this.ideaCollabManager = ideaCollabManager;
        this.ideaManager = ideaManager;
        this.employeeManager = employeeManager;
        this.collabRequestMapper = collabRequestMapper;
        this.ideaCollaborationServiceHelper = ideaCollaborationServiceHelper;
    }


    @Override
    public IdeaColabResponse raiseCollaborationRequest(CollabRequestDto collabRequestDto) {
        Idea idea = ideaManager.getIdeaById(collabRequestDto.getIdeaId());
        Employee employee = employeeManager.getEmployeeById(collabRequestDto.getEmployeeId());
        CollaborationRequest collaborationRequest = collabRequestMapper.toDto(collabRequestDto, idea, employee);
        CollaborationRequest savedCollaborationRequest = ideaCollabManager.createCollaborationRequest(collaborationRequest);
        return collabRequestMapper.toResponse(savedCollaborationRequest);
    }

    @Override
    public void acceptCollaborationRequest(String requestId) {

    }

    @Override
    public void rejectCollaborationRequest(String requestId) {

    }

    @Override
    public void getCollaborationRequestsByIdeaId(String ideaId) {

    }

    @Override
    public void getCollaborationRequestsByEmployeeId(String employeeId) {

    }

    @Override
    public void getCollaborationRequestById(String requestId) {

    }

    @Override
    public Boolean respondToCollaborationRequest(String requestId, CollaborationActionRequestDto collaborationActionRequestDto) {
        CollaborationRequest collaborationRequest = ideaCollabManager.getCollaborationRequestById(requestId);
        if (collaborationRequest != null) {

            collaborationRequest.setStatus(ideaCollaborationServiceHelper.getRequestStatusFromCollaborationActions(collaborationActionRequestDto.getAction()));

            ideaCollabManager.updateCollaborationRequest(collaborationRequest);
            return true;
        }
        return false;
    }
}
