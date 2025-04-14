package com.finbox.idea_collab_service.service;

import com.finbox.idea_collab_service.dto.reponse.IdeaColabResponse;
import com.finbox.idea_collab_service.dto.reponse.IdeaCollaborationsResponse;
import com.finbox.idea_collab_service.dto.request.CollabRequestDto;
import com.finbox.idea_collab_service.dto.request.CollaborationActionRequestDto;
import com.finbox.idea_collab_service.entity.CollaborationRequest;

import java.util.List;

public interface IdeaCollaborationService {
    IdeaColabResponse raiseCollaborationRequest(CollabRequestDto collabRequestDto, String employeeId);

    void acceptCollaborationRequest(String requestId);

    void rejectCollaborationRequest(String requestId);

    IdeaCollaborationsResponse getCollaborationRequestsByIdeaId(String ideaId);

    IdeaCollaborationsResponse getCollaborationRequestsByEmployeeId(String employeeId);

    void getCollaborationRequestById(String requestId);

    Boolean respondToCollaborationRequest(String requestId, CollaborationActionRequestDto collaborationActionRequestDto);
}
