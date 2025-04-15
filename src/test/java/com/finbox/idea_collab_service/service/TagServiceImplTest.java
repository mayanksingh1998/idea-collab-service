package com.finbox.idea_collab_service.service;


import com.finbox.idea_collab_service.entity.Tag;
import com.finbox.idea_collab_service.repository.TagRepository;
import com.finbox.idea_collab_service.service.impl.TagServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class TagServiceImplTest {

    private TagRepository tagRepository;
    private TagServiceImpl tagService;

    @BeforeEach
    void setUp() {
        tagRepository = mock(TagRepository.class);
        tagService = new TagServiceImpl(tagRepository);
    }

    @Test
    void testCreateTag_ShouldSaveAndReturnTag() {
        String tagName = "Innovation";

        Tag tagToSave = new Tag();
        tagToSave.setName(tagName);

        Tag savedTag = new Tag();
        savedTag.setId("tag123");
        savedTag.setName(tagName);

        when(tagRepository.save(any(Tag.class))).thenReturn(savedTag);

        Tag result = tagService.createTag(tagName);

        assertNotNull(result);
        assertEquals("tag123", result.getId());
        assertEquals(tagName, result.getName());

        verify(tagRepository, times(1)).save(any(Tag.class));
    }
}

