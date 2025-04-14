package com.finbox.idea_collab_service.controller;

import com.finbox.idea_collab_service.dto.reponse.IdeaColabResponse;
import com.finbox.idea_collab_service.dto.reponse.IdeaColabSvcResponse;
import com.finbox.idea_collab_service.dto.request.CollabRequestDto;
import com.finbox.idea_collab_service.dto.request.CollaborationActionRequestDto;
import com.finbox.idea_collab_service.service.IdeaCollaborationService;
import com.finbox.idea_collab_service.utils.ResponseBuilder;
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

    @PostMapping(value = "/collaborate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdeaColabSvcResponse<IdeaColabResponse>> raiseCollaborationRequest(
            @NonNull @PathVariable("ideaId") String ideaId,
            @RequestBody CollabRequestDto collabRequestDto) {
        return ResponseBuilder.build(
                ideaCollaborationService.raiseCollaborationRequest(collabRequestDto), HttpStatus.OK);
    }

    @PutMapping(value = "/collaborate/{collaborateId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdeaColabSvcResponse<Boolean>> respondToCollaborationRequest(
            @NonNull @PathVariable("collaborateId") String collaborateId,
            @RequestBody CollaborationActionRequestDto collaborationActionRequestDto) {
        return ResponseBuilder.build(
                ideaCollaborationService.respondToCollaborationRequest(collaborateId, collaborationActionRequestDto), HttpStatus.OK);
    }
}
