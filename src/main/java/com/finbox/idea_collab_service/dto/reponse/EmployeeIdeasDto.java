package com.finbox.idea_collab_service.dto.reponse;

import com.finbox.idea_collab_service.entity.Idea;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeIdeasDto {
    List<Idea> ideas;
}
