package com.finbox.idea_collab_service.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeCollaborationDto {
    private String collaborationId;
    private String collaborationDescription;
    private String collaborationStatus;
    private Timestamp createdAt;
}
