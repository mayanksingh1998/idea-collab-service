package com.finbox.idea_collab_service.controller;

import com.finbox.idea_collab_service.dto.reponse.IdeaColabResponse;
import com.finbox.idea_collab_service.dto.reponse.IdeaColabSvcResponse;
import com.finbox.idea_collab_service.dto.reponse.IdeaResponseDto;
import com.finbox.idea_collab_service.dto.reponse.LoginResponseDto;
import com.finbox.idea_collab_service.dto.request.CreateIdeaRequest;
import com.finbox.idea_collab_service.dto.request.IdeaReactionRequestDto;
import com.finbox.idea_collab_service.entity.Idea;
import com.finbox.idea_collab_service.service.IdeaService;
import com.finbox.idea_collab_service.utils.ResponseBuilder;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/idea")

public class IdeaController {

    private final IdeaService ideaService;

    public IdeaController(IdeaService ideaService) {
        this.ideaService = ideaService;
    }


    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdeaColabSvcResponse<Idea>> createIdea(
            @RequestBody CreateIdeaRequest createIdeaRequest) {
        return ResponseBuilder.build(
                ideaService.createIdea(createIdeaRequest), HttpStatus.OK);
    }

    @PutMapping(value = "/{ideaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdeaColabSvcResponse<Boolean>> reactOnIdea(
            @NonNull @PathVariable("ideaId") String ideaId,
            @RequestBody IdeaReactionRequestDto ideaReactionRequestDto) {
        return ResponseBuilder.build(
                ideaService.reactOnIdea(ideaId, ideaReactionRequestDto), HttpStatus.OK);
    }

    @GetMapping(value = "/{ideaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdeaColabSvcResponse<IdeaResponseDto>> getIdeaById(
            @NonNull @PathVariable("ideaId") String ideaId) {
        return ResponseBuilder.build(
                ideaService.getIdeaById(ideaId), HttpStatus.OK);
    }


}
