package com.finbox.idea_collab_service.repository;

import com.finbox.idea_collab_service.entity.Employee;
import com.finbox.idea_collab_service.entity.Idea;
import com.finbox.idea_collab_service.entity.IdeaStatus;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface IdeaRepository extends JpaRepository<Idea, Integer> {
    Optional<Idea> findIdeaById(String ideaId);
    Optional<Idea> findIdeaByTitle(String title);

    List<Idea> findIdeaByCreatedAtBetween(Timestamp start, Timestamp end, Sort sort);

    List<Idea> findIdeasByCreatedBy_Id(String employeeId, Sort sort);

    List<Idea> findIdeasByStatus(IdeaStatus ideaStatus, Sort sort);

    @Query("SELECT DISTINCT i FROM Idea i " +
            "LEFT JOIN i.tags t " +
            "WHERE (:employeeIds IS NULL OR i.createdBy.id IN :employeeIds) " +
            "AND (:statuses IS NULL OR i.status IN :statuses) " +
            "AND (:tags IS NULL OR t.name IN :tags) " +
            "AND i.createdAt >= :startDate " +
            "AND i.createdAt <= :endDate")
    List<Idea> findFilteredIdeas(
            @Param("employeeIds") List<String> employeeIds,
            @Param("statuses") List<IdeaStatus> statuses,
            @Param("tags") List<String> tags,
            @Param("startDate") Timestamp startDate,
            @Param("endDate") Timestamp endDate,
            Sort sort
    );






}
