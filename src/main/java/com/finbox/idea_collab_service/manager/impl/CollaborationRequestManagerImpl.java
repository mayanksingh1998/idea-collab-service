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
    private final IdeaRepository ideaRepository;
    private final EmployeeRepository employeeRepository;

    public CollaborationRequestManagerImpl(CollaborationRequestRepository collaborationRequestRepository, IdeaRepository ideaRepository, EmployeeRepository employeeRepository) {
        this.collaborationRequestRepository = collaborationRequestRepository;
        this.ideaRepository = ideaRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void createCollaborationRequest(String ideaId, String employeeId, String description) {
        if (ideaId == null || employeeId == null) {
            throw new IllegalArgumentException("Idea ID and Employee ID cannot be null");
        }

        // Check if the idea exists
        Idea idea = ideaRepository.findIdeaById(ideaId)
                .orElseThrow(() -> new ResourceNotFoundException("Idea not found with ID: " + ideaId));

        // Check if the employee exists
        Employee employee = employeeRepository.getEmployeesById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + employeeId));


        // Create and save the collaboration request
        CollaborationRequest collaborationRequest = CollaborationRequest.builder()
                .description(description)
                .status(RequestStatus.PENDING)
                .idea(idea)
                .employee(employee).build();
        collaborationRequestRepository.save(collaborationRequest);

    }

    @Override
    public void acceptCollaborationRequest(String requestId, String employeeId) {
        if (requestId == null || employeeId == null) {
            throw new IllegalArgumentException("Request ID and Employee ID cannot be null");
        }

        // Check if the collaboration request exists
        CollaborationRequest collaborationRequest = collaborationRequestRepository.findCollaborationRequestById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Collaboration request not found with ID: " + requestId));

        // Update the status of the collaboration request to ACCEPTED
        collaborationRequest.setStatus(RequestStatus.ACCEPTED);
        collaborationRequestRepository.save(collaborationRequest);


    }

    @Override
    public void rejectCollaborationRequest(String requestId, String employeeId) {

    }

    @Override
    public void cancelCollaborationRequest(String requestId, String employeeId) {

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
