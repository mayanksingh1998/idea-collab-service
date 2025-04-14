package com.finbox.idea_collab_service.repository;

import com.finbox.idea_collab_service.entity.Idea;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface IdeaRepository extends JpaRepository<Idea, Integer> {
    Optional<Idea> findIdeaById(String ideaId);
    Optional<Idea> findIdeaByTitle(String title);

}
