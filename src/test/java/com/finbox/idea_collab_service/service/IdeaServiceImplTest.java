package com.finbox.idea_collab_service.service;


import com.finbox.idea_collab_service.dto.request.CreateIdeaRequest;
import com.finbox.idea_collab_service.dto.request.IdeaReactionRequestDto;
import com.finbox.idea_collab_service.entity.Employee;
import com.finbox.idea_collab_service.entity.Idea;
import com.finbox.idea_collab_service.manager.EmployeeManager;
import com.finbox.idea_collab_service.manager.IdeaManager;
import com.finbox.idea_collab_service.mapper.IdeaMapper;
import com.finbox.idea_collab_service.repository.TagRepository;
import com.finbox.idea_collab_service.service.impl.IdeaServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class IdeaServiceImplTest {

    @Mock
    private IdeaManager ideaManager;

    @Mock
    private IdeaMapper ideaMapper;

    @Mock
    private EmployeeManager employeeManager;

    @Mock
    private TagRepository tagRepository;

    @InjectMocks
    private IdeaServiceImpl ideaService;

    @Test
    void testCreateIdea() {
        CreateIdeaRequest request = new CreateIdeaRequest();
        request.setTagIds(Collections.emptyList());

        Employee employee = new Employee();
        Idea idea = new Idea();

        when(employeeManager.getEmployeeById("123")).thenReturn(employee);
        when(tagRepository.findAllByIdIn(Collections.emptyList())).thenReturn(Collections.emptyList());
        when(ideaMapper.toIdeaDto(request, employee)).thenReturn(idea);
        when(ideaManager.createOrUpdateIdea(idea)).thenReturn(idea);

        Idea result = ideaService.createIdea(request, "123");

        assertNotNull(result);
        verify(ideaManager).createOrUpdateIdea(idea);
    }

    @Test
    void testGetIdeaById() {
        Idea idea = new Idea();
        idea.setId("idea123");

        when(ideaManager.getIdeaById("idea123")).thenReturn(idea);

        var response = ideaService.getIdeaById("idea123");

        assertNotNull(response);
        assertEquals("idea123", response.getIdea().getId());
        verify(ideaManager).getIdeaById("idea123");
    }

    @Test
    void testGetIdeasByEmployeeId() {
        Idea idea = new Idea();
        when(ideaManager.getIdeasByEmployeeId("emp1")).thenReturn(Collections.singletonList(idea));

        var result = ideaService.getIdeasByEmployeeId("emp1");

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(ideaManager).getIdeasByEmployeeId("emp1");
    }

//    @Test
//    void testReactOnIdeaSuccess() {
//        String ideaId = "idea123";
//        String employeeId = "emp1";
//        var request = new IdeaReactionRequestDto();
//        request.setAction("UPVOTE");
//
//        when(ideaServiceHelper.getAndValidateVoteStatus("UPVOTE")).thenReturn("UPVOTE");
//
//        boolean result = ideaService.reactOnIdea(ideaId, request, employeeId);
//
//        assertTrue(result);
//        verify(ideaManager).voteOnIdea(ideaId, employeeId, "UPVOTE");
//    }

}
