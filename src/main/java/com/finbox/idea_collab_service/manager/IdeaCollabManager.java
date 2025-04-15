package com.finbox.idea_collab_service.manager;

import com.finbox.idea_collab_service.entity.CollaborationRequest;

import java.util.List;

public interface IdeaCollabManager {
    CollaborationRequest createCollaborationRequest(CollaborationRequest collaborationRequest);

    CollaborationRequest updateCollaborationRequest(CollaborationRequest collaborationRequest);


    CollaborationRequest getCollaborationRequestById(String requestId);
}
