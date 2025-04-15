package com.finbox.idea_collab_service.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollaborationRequestResponseDto {
    private String employeeName;
    private String employeeId;
    private EmployeeCollaborationDto employeeCollaborationDto;
}
