package com.finbox.idea_collab_service.service;


import com.finbox.idea_collab_service.dto.enums.CollaborationRequestAction;
import com.finbox.idea_collab_service.dto.reponse.EmployeeCollaborationsResponseDto;
import com.finbox.idea_collab_service.dto.reponse.IdeaColabResponse;
import com.finbox.idea_collab_service.dto.reponse.IdeaCollaborationsResponse;
import com.finbox.idea_collab_service.dto.request.CollabRequestDto;
import com.finbox.idea_collab_service.dto.request.CollaborationActionRequestDto;
import com.finbox.idea_collab_service.entity.CollaborationRequest;
import com.finbox.idea_collab_service.entity.Employee;
import com.finbox.idea_collab_service.entity.Idea;
import com.finbox.idea_collab_service.entity.RequestStatus;
import com.finbox.idea_collab_service.exception.UserNotAllowedForVoteException;
import com.finbox.idea_collab_service.helper.IdeaCollaborationServiceHelper;
import com.finbox.idea_collab_service.manager.CollaborationRequestManager;
import com.finbox.idea_collab_service.manager.EmployeeManager;
import com.finbox.idea_collab_service.manager.IdeaCollabManager;
import com.finbox.idea_collab_service.manager.IdeaManager;
import com.finbox.idea_collab_service.mapper.CollabRequestMapper;
import com.finbox.idea_collab_service.service.impl.IdeaCollaborationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IdeaCollaborationServiceImplTest {

    private IdeaCollabManager ideaCollabManager;
    private IdeaManager ideaManager;
    private EmployeeManager employeeManager;
    private CollabRequestMapper collabRequestMapper;
    private CollaborationRequestManager collaborationRequestManager;
    private IdeaCollaborationServiceHelper ideaCollaborationServiceHelper;

    private IdeaCollaborationServiceImpl service;

    @BeforeEach
    void setUp() {
        ideaCollabManager = mock(IdeaCollabManager.class);
        ideaManager = mock(IdeaManager.class);
        employeeManager = mock(EmployeeManager.class);
        collabRequestMapper = mock(CollabRequestMapper.class);
        collaborationRequestManager = mock(CollaborationRequestManager.class);
        ideaCollaborationServiceHelper = mock(IdeaCollaborationServiceHelper.class);

        service = new IdeaCollaborationServiceImpl(
                ideaCollabManager,
                ideaManager,
                employeeManager,
                collabRequestMapper,
                collaborationRequestManager,
                ideaCollaborationServiceHelper
        );
    }

    @Test
    void testRaiseCollaborationRequest_Success() {
        String employeeId = "emp1";
        String ideaId = "idea1";

        Employee employee = new Employee();
        employee.setId(employeeId);



        Employee employee2 = new Employee();
        employee.setId("employeeId");


        Idea idea = new Idea();
        idea.setId(ideaId);
        idea.setCreatedBy(employee2);


        CollabRequestDto dto = new CollabRequestDto();
        dto.setIdeaId(ideaId);

        CollaborationRequest request = new CollaborationRequest();
        CollaborationRequest savedRequest = new CollaborationRequest();
        IdeaColabResponse response = new IdeaColabResponse();

        when(ideaManager.getIdeaById(ideaId)).thenReturn(idea);
        when(employeeManager.getEmployeeById(employeeId)).thenReturn(employee);
        when(collabRequestMapper.toDto(dto, idea, employee)).thenReturn(request);
        when(ideaCollabManager.createCollaborationRequest(request)).thenReturn(savedRequest);
        when(collabRequestMapper.toResponse(savedRequest)).thenReturn(response);

        IdeaColabResponse result = service.raiseCollaborationRequest(dto, employeeId);

        assertNotNull(result);
        verify(ideaCollabManager).createCollaborationRequest(request);
    }

    @Test
    void testRaiseCollaborationRequest_ThrowsForOwnIdea() {
        String employeeId = "emp1";

        Employee employee = new Employee();
        employee.setId(employeeId);

        Idea idea = new Idea();
        idea.setCreatedBy(employee); // creator is same as employee making request

        CollabRequestDto dto = new CollabRequestDto();
        dto.setIdeaId("idea1");

        when(ideaManager.getIdeaById("idea1")).thenReturn(idea);
        when(employeeManager.getEmployeeById(employeeId)).thenReturn(employee);

        assertThrows(UserNotAllowedForVoteException.class,
                () -> service.raiseCollaborationRequest(dto, employeeId));
    }

    @Test
    void testGetCollaborationRequestsByIdeaId() {
        String ideaId = "idea1";
        List<CollaborationRequest> requests = List.of(new CollaborationRequest());

        IdeaCollaborationsResponse expected = new IdeaCollaborationsResponse();

        when(collaborationRequestManager.getCollaborationRequestsByIdeaId(ideaId)).thenReturn(requests);
        when(collabRequestMapper.toCollaborationRequestResponse(requests)).thenReturn(expected);

        IdeaCollaborationsResponse result = service.getCollaborationRequestsByIdeaId(ideaId);

        assertEquals(expected, result);
    }

    @Test
    void testGetCollaborationRequestsByEmployeeId() {
        String empId = "emp1";
        List<CollaborationRequest> requests = List.of(new CollaborationRequest());

        EmployeeCollaborationsResponseDto expected = new EmployeeCollaborationsResponseDto();

        when(collaborationRequestManager.getCollaborationRequestsByEmployeeId(empId)).thenReturn(requests);
        when(collabRequestMapper.toEmployeeCollaborationResponse(requests)).thenReturn(expected);

        EmployeeCollaborationsResponseDto result = service.getCollaborationRequestsByEmployeeId(empId);

        assertEquals(expected, result);
    }

    @Test
    void testRespondToCollaborationRequest_Valid() {
        String requestId = "req1";
        CollaborationRequest request = new CollaborationRequest();

        CollaborationActionRequestDto dto = new CollaborationActionRequestDto();
        dto.setAction(CollaborationRequestAction.ACCEPTED);

        when(ideaCollabManager.getCollaborationRequestById(requestId)).thenReturn(request);
        when(ideaCollaborationServiceHelper.getRequestStatusFromCollaborationActions(CollaborationRequestAction.ACCEPTED)).thenReturn(RequestStatus.ACCEPTED);

        boolean result = service.respondToCollaborationRequest(requestId, dto);

        assertTrue(result);
        assertEquals(RequestStatus.ACCEPTED, request.getStatus());
        verify(ideaCollabManager).updateCollaborationRequest(request);
    }

    @Test
    void testRespondToCollaborationRequest_InvalidRequest() {
        when(ideaCollabManager.getCollaborationRequestById("invalid")).thenReturn(null);
        boolean result = service.respondToCollaborationRequest("invalid", new CollaborationActionRequestDto());
        assertFalse(result);
    }
}

