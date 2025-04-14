package com.finbox.idea_collab_service.mapper;

import com.finbox.idea_collab_service.dto.reponse.IdeaResponseDto;
import com.finbox.idea_collab_service.dto.request.CreateIdeaRequest;
import com.finbox.idea_collab_service.entity.Employee;
import com.finbox.idea_collab_service.entity.Idea;
import com.finbox.idea_collab_service.entity.IdeaStatus;
import org.springframework.stereotype.Component;

@Component
public class IdeaMapper {

    public Idea toIdeaDto(CreateIdeaRequest idea, Employee employee) {
        Idea ideaDto = new Idea();
        ideaDto.setTitle(idea.getTitle());
        ideaDto.setDescription(idea.getDescription());
        ideaDto.setStatus(IdeaStatus.DRAFT);
        ideaDto.setCreatedBy(employee);
        ideaDto.setVotesCount(0);
        return ideaDto;
    }

    public IdeaResponseDto toIdeaResponseDto(Idea idea) {
        IdeaResponseDto ideaResponseDto = new IdeaResponseDto();
        ideaResponseDto.setIdea(idea);
        return ideaResponseDto;
    }

}
