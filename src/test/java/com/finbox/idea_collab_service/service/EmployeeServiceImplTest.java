package com.finbox.idea_collab_service.service;


import com.finbox.idea_collab_service.dto.reponse.EmployeeIdeasDto;
import com.finbox.idea_collab_service.dto.request.AddEmployeeRequestDto;
import com.finbox.idea_collab_service.entity.Employee;
import com.finbox.idea_collab_service.entity.EmployeeCredential;
import com.finbox.idea_collab_service.entity.Idea;
import com.finbox.idea_collab_service.helper.EmployeeServiceHelper;
import com.finbox.idea_collab_service.manager.EmployeeManager;
import com.finbox.idea_collab_service.mapper.EmployeeMapper;
import com.finbox.idea_collab_service.service.IdeaService;
import com.finbox.idea_collab_service.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EmployeeServiceImplTest {

    private EmployeeManager employeeManager;
    private EmployeeServiceHelper employeeServiceHelper;
    private IdeaService ideaService;
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void setUp() {
        employeeManager = mock(EmployeeManager.class);
        employeeServiceHelper = mock(EmployeeServiceHelper.class);
        ideaService = mock(IdeaService.class);
        employeeService = new EmployeeServiceImpl(employeeManager, employeeServiceHelper, ideaService);
    }

    @Test
    void testGetEmployeeById_ValidId() {
        String employeeId = "emp123";
        Employee mockEmployee = new Employee();
        mockEmployee.setId(employeeId);

        when(employeeManager.getEmployeeById(employeeId)).thenReturn(mockEmployee);

        Employee result = employeeService.getEmployeeById(employeeId);

        assertNotNull(result);
        assertEquals(employeeId, result.getId());
    }

    @Test
    void testGetEmployeeById_NullOrEmptyId() {
        assertNull(employeeService.getEmployeeById(null));
        assertNull(employeeService.getEmployeeById(""));
    }

    @Test
    void testOnboardEmployee_Success() {
        AddEmployeeRequestDto requestDto = new AddEmployeeRequestDto();
        requestDto.setEmail("john@example.com");
        requestDto.setPassword("securePassword");

        Employee employeeEntity = new Employee();
        employeeEntity.setEmail(requestDto.getEmail());

        EmployeeCredential credential = new EmployeeCredential();
        employeeEntity.setEmployeeCredential(credential);

        // Mock static method from mapper
        mockStatic(EmployeeMapper.class).when(() -> EmployeeMapper.toDTO(requestDto)).thenReturn(employeeEntity);
        when(employeeServiceHelper.createEmployeeCredentials(eq(requestDto.getEmail()), eq(requestDto.getPassword()), any(Employee.class)))
                .thenReturn(credential);
        when(employeeManager.persitEmployee(employeeEntity)).thenReturn(employeeEntity);

        Employee onboardedEmployee = employeeService.onboardEmployee(requestDto);

        assertNotNull(onboardedEmployee);
        assertEquals(requestDto.getEmail(), onboardedEmployee.getEmail());
        assertEquals(credential, onboardedEmployee.getEmployeeCredential());
    }

    @Test
    void testOnboardEmployee_NullRequest() {
        assertNull(employeeService.onboardEmployee(null));
    }

    @Test
    void testGetEmployeeIdeas_ReturnsIdeas() {
        String employeeId = "emp123";
        Idea idea1 = new Idea();
        idea1.setTitle("Idea One");

        Idea idea2 = new Idea();
        idea2.setTitle("Idea Two");

        when(ideaService.getIdeasByEmployeeId(employeeId)).thenReturn(List.of(idea1, idea2));

        EmployeeIdeasDto result = employeeService.getEmployeeIdeas(employeeId);

        assertNotNull(result);
        assertEquals(2, result.getIdeas().size());
        assertEquals("Idea One", result.getIdeas().get(0).getTitle());
    }
}

