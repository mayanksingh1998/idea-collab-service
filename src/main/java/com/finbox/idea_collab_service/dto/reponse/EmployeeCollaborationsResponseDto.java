package com.finbox.idea_collab_service.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeCollaborationsResponseDto {
    List<EmployeeCollaborationDto> employeeCollaborationDtos;
}
