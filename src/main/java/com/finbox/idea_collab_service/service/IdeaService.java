package com.finbox.idea_collab_service.service;

import com.finbox.idea_collab_service.dto.reponse.AllIdeasResponseDto;
import com.finbox.idea_collab_service.dto.reponse.IdeaReactionsResponseDto;
import com.finbox.idea_collab_service.dto.reponse.IdeaResponse;
import com.finbox.idea_collab_service.dto.request.CreateIdeaRequest;
import com.finbox.idea_collab_service.dto.request.IdeaFilterRequest;
import com.finbox.idea_collab_service.dto.request.IdeaReactionRequestDto;
import com.finbox.idea_collab_service.entity.Idea;

import java.util.List;

public interface IdeaService {
    Idea createIdea(CreateIdeaRequest createIdeaRequest, String employeeId);

    IdeaResponse getIdeaById(String ideaId);

    AllIdeasResponseDto getFilteredIdeas(IdeaFilterRequest request);

    IdeaReactionsResponseDto getIdeaReactionsById(String ideaId);


    Boolean reactOnIdea(String ideaId, IdeaReactionRequestDto ideaReactionRequestDto, String employeeId);

    Boolean postIdea(String ideaId, String employeeId);

    List<Idea> getIdeasByEmployeeId(String employeeId);

}
