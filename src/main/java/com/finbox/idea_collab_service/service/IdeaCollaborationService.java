package com.finbox.idea_collab_service.service;

import com.finbox.idea_collab_service.dto.reponse.EmployeeCollaborationsResponseDto;
import com.finbox.idea_collab_service.dto.reponse.IdeaColabResponse;
import com.finbox.idea_collab_service.dto.reponse.IdeaCollaborationsResponse;
import com.finbox.idea_collab_service.dto.request.CollabRequestDto;
import com.finbox.idea_collab_service.dto.request.CollaborationActionRequestDto;
import com.finbox.idea_collab_service.entity.CollaborationRequest;

import java.util.List;

public interface IdeaCollaborationService {
    IdeaColabResponse raiseCollaborationRequest(CollabRequestDto collabRequestDto, String employeeId);

    IdeaCollaborationsResponse getCollaborationRequestsByIdeaId(String ideaId);

    EmployeeCollaborationsResponseDto getCollaborationRequestsByEmployeeId(String employeeId);


    Boolean respondToCollaborationRequest(String requestId, CollaborationActionRequestDto collaborationActionRequestDto);
}
