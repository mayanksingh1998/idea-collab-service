package com.finbox.idea_collab_service.manager;

import com.finbox.idea_collab_service.entity.CollaborationRequest;

public interface IdeaCollabManager {
    CollaborationRequest createCollaborationRequest(CollaborationRequest collaborationRequest);

    CollaborationRequest updateCollaborationRequest(CollaborationRequest collaborationRequest);


    void acceptCollaborationRequest(String requestId);

    void rejectCollaborationRequest(String requestId);

    void getCollaborationRequestsByIdeaId(String ideaId);

    void getCollaborationRequestsByEmployeeId(String employeeId);

    CollaborationRequest getCollaborationRequestById(String requestId);
}
