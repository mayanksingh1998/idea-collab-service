package com.finbox.idea_collab_service.manager;

import com.finbox.idea_collab_service.entity.CollaborationRequest;

import java.util.List;

public interface CollaborationRequestManager {

    List<CollaborationRequest> getCollaborationRequestsByIdeaId(String ideaId);

    List<CollaborationRequest> getCollaborationRequestsByEmployeeId(String employeeId);
}
