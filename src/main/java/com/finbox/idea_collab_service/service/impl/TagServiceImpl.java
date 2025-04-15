package com.finbox.idea_collab_service.service.impl;

import com.finbox.idea_collab_service.entity.Tag;
import com.finbox.idea_collab_service.repository.TagRepository;
import com.finbox.idea_collab_service.service.TagService;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag createTag(String tagName) {
        Tag tag = new Tag();
        tag.setName(tagName);
        return tagRepository.save(tag);
    }
}
