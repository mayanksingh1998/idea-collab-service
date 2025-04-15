package com.finbox.idea_collab_service.dto.reponse;

import com.finbox.idea_collab_service.entity.Employee;
import com.finbox.idea_collab_service.entity.VoteStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeIdeaReactionResponse {
    private Employee employee;
    private VoteStatus voteStatus;
}
