package com.finbox.idea_collab_service.manager.impl;

import com.finbox.idea_collab_service.entity.CollaborationRequest;
import com.finbox.idea_collab_service.entity.Employee;
import com.finbox.idea_collab_service.entity.Idea;
import com.finbox.idea_collab_service.entity.RequestStatus;
import com.finbox.idea_collab_service.exception.ResourceNotFoundException;
import com.finbox.idea_collab_service.manager.CollaborationRequestManager;
import com.finbox.idea_collab_service.repository.CollaborationRequestRepository;
import com.finbox.idea_collab_service.repository.EmployeeRepository;
import com.finbox.idea_collab_service.repository.IdeaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CollaborationRequestManagerImpl implements CollaborationRequestManager {

    private final CollaborationRequestRepository collaborationRequestRepository;

    public CollaborationRequestManagerImpl(CollaborationRequestRepository collaborationRequestRepository) {
        this.collaborationRequestRepository = collaborationRequestRepository;
    }

    @Override
    public List<CollaborationRequest> getCollaborationRequestsByIdeaId(String ideaId) {
        return collaborationRequestRepository.findCollaborationRequestsByIdeaId(ideaId);
    }

    @Override
    public List<CollaborationRequest> getCollaborationRequestsByEmployeeId(String employeeId) {
       return collaborationRequestRepository.findCollaborationRequestsByEmployeeId(employeeId);
    }
}
