package com.finbox.idea_collab_service.controller;

import com.finbox.idea_collab_service.dto.reponse.AllIdeasResponseDto;
import com.finbox.idea_collab_service.dto.reponse.IdeaColabSvcResponse;
import com.finbox.idea_collab_service.dto.reponse.IdeaReactionsResponseDto;
import com.finbox.idea_collab_service.dto.reponse.IdeaResponse;
import com.finbox.idea_collab_service.dto.request.CreateIdeaRequest;
import com.finbox.idea_collab_service.dto.request.IdeaFilterRequest;
import com.finbox.idea_collab_service.dto.request.IdeaReactionRequestDto;
import com.finbox.idea_collab_service.entity.Idea;
import com.finbox.idea_collab_service.entity.IdeaStatus;
import com.finbox.idea_collab_service.service.IdeaService;
import com.finbox.idea_collab_service.utils.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/idea")

public class IdeaController {

    private final IdeaService ideaService;

    public IdeaController(IdeaService ideaService) {
        this.ideaService = ideaService;
    }


    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdeaColabSvcResponse<Idea>> createIdea(
            @RequestBody CreateIdeaRequest createIdeaRequest, HttpServletRequest request) {
        String employeeId = (String) request.getAttribute("employeeId");
        return ResponseBuilder.build(
                ideaService.createIdea(createIdeaRequest, employeeId), HttpStatus.OK);
    }

    @PutMapping(value = "/{ideaId}/react", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdeaColabSvcResponse<Boolean>> reactOnIdea(
            @NonNull @PathVariable("ideaId") String ideaId,
            @RequestBody IdeaReactionRequestDto ideaReactionRequestDto, HttpServletRequest request) {
        String employeeId = (String) request.getAttribute("employeeId");
        return ResponseBuilder.build(
                ideaService.reactOnIdea(ideaId, ideaReactionRequestDto, employeeId), HttpStatus.OK);
    }

    @PutMapping(value = "/{ideaId}/post", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdeaColabSvcResponse<Boolean>> postIdea(
            @NonNull @PathVariable("ideaId") String ideaId,
            HttpServletRequest request) {
        String employeeId = (String) request.getAttribute("employeeId");
        return ResponseBuilder.build(
                ideaService.postIdea(ideaId, employeeId), HttpStatus.OK);
    }

    @GetMapping(value = "/{ideaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdeaColabSvcResponse<IdeaResponse>> getIdeaById(
            @NonNull @PathVariable("ideaId") String ideaId) {
        return ResponseBuilder.build(
                ideaService.getIdeaById(ideaId), HttpStatus.OK);
    }

    @GetMapping(value = "/{ideaId}/reactions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdeaColabSvcResponse<IdeaReactionsResponseDto>> getIdeaReactionsById(
            @NonNull @PathVariable("ideaId") String ideaId) {
        return ResponseBuilder.build(
                ideaService.getIdeaReactionsById(ideaId), HttpStatus.OK);
    }

    @PostMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdeaColabSvcResponse<AllIdeasResponseDto>> getFilteredIdeas(
            @RequestBody IdeaFilterRequest request) {
        return ResponseBuilder.build(
                ideaService.getFilteredIdeas(request), HttpStatus.OK);
    }

//    {
//        "employeeIds": ["emp123", "emp456"],
//        "statuses": ["APPROVED", "OPEN"],
//        "tags": ["AI", "Tech"],
//        "startDate": "2024-12-01T00:00:00",
//            "endDate": "2025-04-10T23:59:59",
//            "sortBy": "votesCount",
//            "order": "desc"
//    }


}
