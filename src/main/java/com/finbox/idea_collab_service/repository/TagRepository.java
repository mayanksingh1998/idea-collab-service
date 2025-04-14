package com.finbox.idea_collab_service.repository;

import com.finbox.idea_collab_service.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByName(String name);
    Optional<Tag> findById(String id);
    List<Tag> findAllByIdIn(List<String> ids);

}
