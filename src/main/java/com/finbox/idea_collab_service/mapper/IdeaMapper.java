package com.finbox.idea_collab_service.mapper;

import com.finbox.idea_collab_service.dto.reponse.AllIdeasResponseDto;
import com.finbox.idea_collab_service.dto.reponse.EmployeeIdeaReactionResponse;
import com.finbox.idea_collab_service.dto.reponse.IdeaReactionsResponseDto;
import com.finbox.idea_collab_service.dto.request.CreateIdeaRequest;
import com.finbox.idea_collab_service.entity.Employee;
import com.finbox.idea_collab_service.entity.Idea;
import com.finbox.idea_collab_service.entity.IdeaReaction;
import com.finbox.idea_collab_service.entity.IdeaStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class IdeaMapper {

    private int initialVotesCount = 0;

    public Idea toIdeaDto(CreateIdeaRequest idea, Employee employee) {
        Idea ideaDto = new Idea();
        ideaDto.setTitle(idea.getTitle());
        ideaDto.setDescription(idea.getDescription());
        ideaDto.setStatus(IdeaStatus.DRAFT);
        ideaDto.setCreatedBy(employee);
        ideaDto.setVotesCount(initialVotesCount);
        return ideaDto;
    }

    public IdeaReactionsResponseDto toIdeaReactionsResponseDto(List<IdeaReaction> ideaReactionList) {
        List<EmployeeIdeaReactionResponse> employeeIdeaReactionResponses = new ArrayList<>();
        for (IdeaReaction ideaReaction : ideaReactionList) {
            EmployeeIdeaReactionResponse employeeIdeaReactionResponse = new EmployeeIdeaReactionResponse();
            employeeIdeaReactionResponse.setEmployee(ideaReaction.getEmployee());
            employeeIdeaReactionResponse.setVoteStatus(ideaReaction.getVoteStatus());
            employeeIdeaReactionResponses.add(employeeIdeaReactionResponse);
        }
        return IdeaReactionsResponseDto.builder().employeeIdeaReactions(employeeIdeaReactionResponses).build();
    }

    public AllIdeasResponseDto toAllIdeasResponseDto(List<Idea> ideas) {
        return AllIdeasResponseDto.builder().ideas(ideas).build();
    }

}
