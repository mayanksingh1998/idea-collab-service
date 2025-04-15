package com.finbox.idea_collab_service.controller;

import com.finbox.idea_collab_service.dto.reponse.IdeaColabResponse;
import com.finbox.idea_collab_service.dto.reponse.IdeaColabSvcResponse;
import com.finbox.idea_collab_service.dto.reponse.IdeaCollaborationsResponse;
import com.finbox.idea_collab_service.dto.request.CollabRequestDto;
import com.finbox.idea_collab_service.dto.request.CollaborationActionRequestDto;
import com.finbox.idea_collab_service.service.IdeaCollaborationService;
import com.finbox.idea_collab_service.utils.ResponseBuilder;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/idea")
@RestController

public class IdeaCollaborationController {

    private final IdeaCollaborationService ideaCollaborationService;

    public IdeaCollaborationController(IdeaCollaborationService ideaCollaborationService) {
        this.ideaCollaborationService = ideaCollaborationService;
    }

    @Operation(summary = "Raise a collaboration request")
    @PostMapping(value = "/collaborate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdeaColabSvcResponse<IdeaColabResponse>> raiseCollaborationRequest(
            @RequestBody CollabRequestDto collabRequestDto,
            HttpServletRequest request) {
        String employeeId = (String) request.getAttribute("employeeId");
        return ResponseBuilder.build(
                ideaCollaborationService.raiseCollaborationRequest(collabRequestDto, employeeId), HttpStatus.OK);
    }

    @Operation(summary = "Change collab request status")
    @PutMapping(value = "/collaborate/{collaborateId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdeaColabSvcResponse<Boolean>> respondToCollaborationRequest(
            @NonNull @PathVariable("collaborateId") String collaborateId,
            @RequestBody CollaborationActionRequestDto collaborationActionRequestDto) {
        return ResponseBuilder.build(
                ideaCollaborationService.respondToCollaborationRequest(collaborateId, collaborationActionRequestDto), HttpStatus.OK);
    }

    @Operation(summary = "Get collaboration requests by idea ID")
    @GetMapping(value = "/{ideaId}/collaborations", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdeaColabSvcResponse<IdeaCollaborationsResponse>> getIdeaCollaborations(
            @NonNull @PathVariable("ideaId") String ideaId) {
        return ResponseBuilder.build(
                ideaCollaborationService.getCollaborationRequestsByIdeaId(ideaId), HttpStatus.OK);
    }
}
