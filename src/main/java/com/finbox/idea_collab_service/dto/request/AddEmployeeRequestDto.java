package com.finbox.idea_collab_service.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddEmployeeRequestDto {
    private String name;
    private String email;
    private String role;
    private String department;
    private String location;
    private String managerId;
    private String startDate;
    private String endDate;

    private String password;
}
