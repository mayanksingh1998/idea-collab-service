package com.finbox.idea_collab_service.mapper;

import com.finbox.idea_collab_service.dto.reponse.IdeaColabResponse;
import com.finbox.idea_collab_service.dto.reponse.IdeaCollaborationsResponse;
import com.finbox.idea_collab_service.dto.request.CollabRequestDto;
import com.finbox.idea_collab_service.entity.CollaborationRequest;
import com.finbox.idea_collab_service.entity.Employee;
import com.finbox.idea_collab_service.entity.Idea;
import com.finbox.idea_collab_service.entity.RequestStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CollabRequestMapper {


     public CollaborationRequest toDto(CollabRequestDto collabRequest, Idea idea, Employee employee) {
            CollaborationRequest collaborationRequest = new CollaborationRequest();
            collaborationRequest.setIdea(idea);
            collaborationRequest.setDescription(collabRequest.getDescription());
            collaborationRequest.setEmployee(employee);
            collaborationRequest.setStatus(RequestStatus.PENDING);
            return collaborationRequest;
     }

     public IdeaColabResponse toResponse(CollaborationRequest collaborationRequest) {
           return IdeaColabResponse.builder()
                   .collaborationRequest(collaborationRequest)
                   .build();
     }

     public IdeaCollaborationsResponse toCollaborationRequestResponse(List<CollaborationRequest> collaborationRequests) {
           return IdeaCollaborationsResponse.builder()
                   .collaborationRequests(collaborationRequests)
                   .build();
     }

    // public CollabRequest toEntity(CollabRequestDTO collabRequestDTO) {
    //     // Implement the mapping logic
    // }
}
