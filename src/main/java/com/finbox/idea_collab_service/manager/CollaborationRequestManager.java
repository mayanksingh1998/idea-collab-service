package com.finbox.idea_collab_service.manager;

import com.finbox.idea_collab_service.entity.CollaborationRequest;

import java.util.List;

public interface CollaborationRequestManager {
    void createCollaborationRequest(String ideaId, String employeeId, String description);

    void acceptCollaborationRequest(String requestId, String employeeId);

    void rejectCollaborationRequest(String requestId, String employeeId);

    void cancelCollaborationRequest(String requestId, String employeeId);

    List<CollaborationRequest> getCollaborationRequestsByIdeaId(String ideaId);

    List<CollaborationRequest> getCollaborationRequestsByEmployeeId(String employeeId);
}
