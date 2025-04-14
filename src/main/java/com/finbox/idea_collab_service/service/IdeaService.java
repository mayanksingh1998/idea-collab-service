package com.finbox.idea_collab_service.service;

import com.finbox.idea_collab_service.dto.reponse.IdeaResponseDto;
import com.finbox.idea_collab_service.dto.request.CreateIdeaRequest;
import com.finbox.idea_collab_service.dto.request.IdeaReactionRequestDto;
import com.finbox.idea_collab_service.entity.Idea;

import java.util.List;

public interface IdeaService {
    Idea createIdea(CreateIdeaRequest createIdeaRequest);

    IdeaResponseDto getIdeaById(String ideaId);

    Idea getIdeaByTitle(String title);

    Idea updateIdeaStatus(String ideaId, CreateIdeaRequest createIdeaRequest);


    void deleteIdea(String ideaId);

    Boolean reactOnIdea(String ideaId, IdeaReactionRequestDto ideaReactionRequestDto, String employeeId);

    Idea unvoteIdea(String ideaId, String employeeId);

    List<IdeaResponseDto> getIdeasByEmployeeId(String employeeId);

    Idea getIdeaByCollaborationRequestId(String requestId);


}
