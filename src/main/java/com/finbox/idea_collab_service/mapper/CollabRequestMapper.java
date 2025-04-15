package com.finbox.idea_collab_service.mapper;

import com.finbox.idea_collab_service.dto.reponse.*;
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
         List<CollaborationRequestResponseDto> collaborationRequestResponseDto = collaborationRequests.stream()
                 .map(collaborationRequest -> {
                     CollaborationRequestResponseDto dto = new CollaborationRequestResponseDto();
                     dto.setEmployeeCollaborationDto(EmployeeCollaborationDto.builder()
                             .collaborationId(collaborationRequest.getId())
                             .collaborationDescription(collaborationRequest.getDescription())
                             .collaborationStatus(collaborationRequest.getStatus().toString())
                             .createdAt(collaborationRequest.getRequestAt()).build());
                     dto.setEmployeeId(collaborationRequest.getEmployee().getId());
                        dto.setEmployeeName(collaborationRequest.getEmployee().getName());
                     return dto;
                 })
                 .toList();

         return IdeaCollaborationsResponse.builder()
                 .collaborationRequestResponseDtos(collaborationRequestResponseDto)
                 .build();
     }

    public EmployeeCollaborationsResponseDto toEmployeeCollaborationResponse(List<CollaborationRequest> collaborationRequests) {
        List<EmployeeCollaborationDto> collaborationRequestResponseDto = collaborationRequests.stream()
                .map(collaborationRequest -> {
                    EmployeeCollaborationDto dto = new EmployeeCollaborationDto();
                    dto.setCollaborationId(collaborationRequest.getId());
                    dto.setCollaborationDescription(collaborationRequest.getDescription());
                    dto.setCollaborationStatus(collaborationRequest.getStatus().toString());
                    dto.setCreatedAt(collaborationRequest.getRequestAt());
                    return dto;
                })
                .toList();

        return EmployeeCollaborationsResponseDto.builder()
                .employeeCollaborationDtos(collaborationRequestResponseDto)
                .build();
    }
}
