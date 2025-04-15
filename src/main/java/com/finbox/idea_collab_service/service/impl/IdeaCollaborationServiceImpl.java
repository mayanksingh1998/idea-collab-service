package com.finbox.idea_collab_service.service.impl;

import com.finbox.idea_collab_service.dto.reponse.EmployeeCollaborationsResponseDto;
import com.finbox.idea_collab_service.dto.reponse.IdeaColabResponse;
import com.finbox.idea_collab_service.dto.reponse.IdeaCollaborationsResponse;
import com.finbox.idea_collab_service.dto.request.CollabRequestDto;
import com.finbox.idea_collab_service.dto.request.CollaborationActionRequestDto;
import com.finbox.idea_collab_service.entity.CollaborationRequest;
import com.finbox.idea_collab_service.entity.Employee;
import com.finbox.idea_collab_service.entity.Idea;
import com.finbox.idea_collab_service.helper.IdeaCollaborationServiceHelper;
import com.finbox.idea_collab_service.manager.CollaborationRequestManager;
import com.finbox.idea_collab_service.manager.EmployeeManager;
import com.finbox.idea_collab_service.manager.IdeaCollabManager;
import com.finbox.idea_collab_service.manager.IdeaManager;
import com.finbox.idea_collab_service.mapper.CollabRequestMapper;
import com.finbox.idea_collab_service.service.IdeaCollaborationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdeaCollaborationServiceImpl implements IdeaCollaborationService {
    private final IdeaCollabManager ideaCollabManager;
    private final IdeaManager ideaManager;
    private final EmployeeManager employeeManager;
    private final CollabRequestMapper collabRequestMapper;
    private final CollaborationRequestManager collaborationRequestManager;

    private final IdeaCollaborationServiceHelper ideaCollaborationServiceHelper;

    public IdeaCollaborationServiceImpl(IdeaCollabManager ideaCollabManager, IdeaManager ideaManager, EmployeeManager employeeManager, CollabRequestMapper collabRequestMapper, CollaborationRequestManager collaborationRequestManager, IdeaCollaborationServiceHelper ideaCollaborationServiceHelper) {
        this.ideaCollabManager = ideaCollabManager;
        this.ideaManager = ideaManager;
        this.employeeManager = employeeManager;
        this.collabRequestMapper = collabRequestMapper;
        this.collaborationRequestManager = collaborationRequestManager;
        this.ideaCollaborationServiceHelper = ideaCollaborationServiceHelper;
    }


    @Override
    public IdeaColabResponse raiseCollaborationRequest(CollabRequestDto collabRequestDto, String employeeId) {
        Idea idea = ideaManager.getIdeaById(collabRequestDto.getIdeaId());
        Employee employee = employeeManager.getEmployeeById(employeeId);
        CollaborationRequest collaborationRequest = collabRequestMapper.toDto(collabRequestDto, idea, employee);
        CollaborationRequest savedCollaborationRequest = ideaCollabManager.createCollaborationRequest(collaborationRequest);
        return collabRequestMapper.toResponse(savedCollaborationRequest);
    }

    @Override
    public IdeaCollaborationsResponse getCollaborationRequestsByIdeaId(String ideaId) {
        List<CollaborationRequest> collaborationRequests = collaborationRequestManager.getCollaborationRequestsByIdeaId(ideaId);
        return collabRequestMapper.toCollaborationRequestResponse(collaborationRequests);
    }

    @Override
    public EmployeeCollaborationsResponseDto getCollaborationRequestsByEmployeeId(String employeeId) {
        List<CollaborationRequest> collaborationRequests = collaborationRequestManager.getCollaborationRequestsByEmployeeId(employeeId);
        return collabRequestMapper.toEmployeeCollaborationResponse(collaborationRequests);

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
