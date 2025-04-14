package com.finbox.idea_collab_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateIdeaRequest {
    private String title;
    private String description;
    private String employeeId;
}
