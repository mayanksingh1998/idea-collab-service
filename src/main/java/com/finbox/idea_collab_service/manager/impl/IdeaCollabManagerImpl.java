package com.finbox.idea_collab_service.manager.impl;

import com.finbox.idea_collab_service.entity.CollaborationRequest;
import com.finbox.idea_collab_service.manager.IdeaCollabManager;
import com.finbox.idea_collab_service.repository.CollaborationRequestRepository;
import org.springframework.stereotype.Component;

@Component
public class IdeaCollabManagerImpl implements IdeaCollabManager {

    private final CollaborationRequestRepository collaborationRequestRepository;

    public IdeaCollabManagerImpl(CollaborationRequestRepository collaborationRequestRepository) {
        this.collaborationRequestRepository = collaborationRequestRepository;
    }

    @Override
    public CollaborationRequest createCollaborationRequest(CollaborationRequest collaborationRequest) {
        return collaborationRequestRepository.save(collaborationRequest);
    }

    @Override
    public CollaborationRequest updateCollaborationRequest(CollaborationRequest collaborationRequest) {
        return collaborationRequestRepository.save(collaborationRequest);
    }

    @Override
    public void acceptCollaborationRequest(String requestId) {
        // Implementation here
    }

    @Override
    public void rejectCollaborationRequest(String requestId) {
        // Implementation here
    }



    @Override
    public void getCollaborationRequestsByEmployeeId(String employeeId) {
        // Implementation here
    }

    @Override
    public CollaborationRequest getCollaborationRequestById(String requestId) {
        return collaborationRequestRepository.findCollaborationRequestById(requestId)
                .orElseThrow(() -> new RuntimeException("Collaboration request not found"));
    }
}
