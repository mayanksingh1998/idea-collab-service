package com.finbox.idea_collab_service.dto.request;

import com.finbox.idea_collab_service.entity.IdeaStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IdeaFilterRequest {
    private List<String> employeeIds;
    private List<IdeaStatus> statuses;
    private List<String> tags;
    private Timestamp startDate;
    private Timestamp endDate;
    private String sortBy = "createdAt";
    private String order = "desc";
}
