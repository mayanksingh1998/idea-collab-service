package com.finbox.idea_collab_service.repository;

import com.finbox.idea_collab_service.entity.CollaborationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CollaborationRequestRepository extends JpaRepository<CollaborationRequest, Integer> {
    Optional<CollaborationRequest> findCollaborationRequestById(String requestId);
    Optional<CollaborationRequest> findCollaborationRequestByIdeaIdAndEmployeeId(String ideaId, String employeeId);
    List<CollaborationRequest> findCollaborationRequestsByEmployeeId(String employeeId);
    List<CollaborationRequest> findCollaborationRequestsByIdeaId(String ideaId);
}
