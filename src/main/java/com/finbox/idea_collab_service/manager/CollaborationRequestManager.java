package com.finbox.idea_collab_service.manager;

import java.util.List;

public interface CollaborationRequestManager {
    void createCollaborationRequest(String ideaId, String employeeId, String description);

    void acceptCollaborationRequest(String requestId, String employeeId);

    void rejectCollaborationRequest(String requestId, String employeeId);

    void cancelCollaborationRequest(String requestId, String employeeId);

    List<CollaborationRequestManager> getCollaborationRequestsByIdeaId(String ideaId);

    List<CollaborationRequestManager> getCollaborationRequestsByEmployeeId(String employeeId);
}
