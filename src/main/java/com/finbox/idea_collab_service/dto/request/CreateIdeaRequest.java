package com.finbox.idea_collab_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateIdeaRequest {
    private String title;
    private String description;
    private String employeeId;
    private List<String> tagIds;
}
