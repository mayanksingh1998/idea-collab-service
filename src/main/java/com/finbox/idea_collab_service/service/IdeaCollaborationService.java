package com.finbox.idea_collab_service.service;

import com.finbox.idea_collab_service.dto.reponse.IdeaColabResponse;
import com.finbox.idea_collab_service.dto.request.CollabRequestDto;
import com.finbox.idea_collab_service.dto.request.CollaborationActionRequestDto;

public interface IdeaCollaborationService {
    IdeaColabResponse raiseCollaborationRequest(CollabRequestDto collabRequestDto);

    void acceptCollaborationRequest(String requestId);

    void rejectCollaborationRequest(String requestId);

    void getCollaborationRequestsByIdeaId(String ideaId);

    void getCollaborationRequestsByEmployeeId(String employeeId);

    void getCollaborationRequestById(String requestId);

    Boolean respondToCollaborationRequest(String requestId, CollaborationActionRequestDto collaborationActionRequestDto);
}
