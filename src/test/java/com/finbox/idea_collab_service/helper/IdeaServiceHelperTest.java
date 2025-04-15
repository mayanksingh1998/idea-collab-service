package com.finbox.idea_collab_service.helper;

import com.finbox.idea_collab_service.dto.enums.IdeaReactionAction;
import com.finbox.idea_collab_service.entity.Tag;
import com.finbox.idea_collab_service.entity.VoteStatus;
import com.finbox.idea_collab_service.exception.InvalidIdeaReactionException;
import com.finbox.idea_collab_service.repository.TagRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IdeaServiceHelperTest {

    private TagRepository tagRepository;
    private IdeaServiceHelper ideaServiceHelper;

    @BeforeEach
    void setUp() {
        tagRepository = mock(TagRepository.class);
        ideaServiceHelper = new IdeaServiceHelper();
        // Inject mock manually since no @RequiredArgsConstructor or constructor injection in original class
        var tagRepositoryField = IdeaServiceHelper.class.getDeclaredFields()[0];
        tagRepositoryField.setAccessible(true);
        try {
            tagRepositoryField.set(ideaServiceHelper, tagRepository);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to inject tagRepository", e);
        }
    }

    @Test
    void testGetAndValidateVoteStatus_UpVote() {
        assertEquals(VoteStatus.UPVOTE, ideaServiceHelper.getAndValidateVoteStatus(IdeaReactionAction.UP_VOTE));
    }

    @Test
    void testGetAndValidateVoteStatus_DownVote() {
        assertEquals(VoteStatus.DOWNVOTE, ideaServiceHelper.getAndValidateVoteStatus(IdeaReactionAction.DOWN_VOTE));
    }

    @Test
    void testGetAndValidateVoteStatus_NeutralVote() {
        assertEquals(VoteStatus.NEUTRAL, ideaServiceHelper.getAndValidateVoteStatus(IdeaReactionAction.NEUTRAL_VOTE));
    }

    @Test
    void testGetTags_ReturnsAllTags() {
        Tag tag1 = new Tag();
        tag1.setName("Tech");
        Tag tag2 = new Tag();
        tag2.setName("Innovation");

        List<Tag> tags = Arrays.asList(tag1, tag2);
        when(tagRepository.findAll()).thenReturn(tags);

        List<Tag> result = ideaServiceHelper.getTags();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Tech", result.get(0).getName());
        assertEquals("Innovation", result.get(1).getName());

        verify(tagRepository, times(1)).findAll();
    }
}

