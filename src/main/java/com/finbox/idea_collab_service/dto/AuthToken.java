package com.finbox.idea_collab_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthToken {
    private String employeeId;
    private String accessToken;
    private Timestamp expiry;
}
