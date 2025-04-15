    package com.finbox.idea_collab_service.controller;

    import com.finbox.idea_collab_service.dto.reponse.EmployeeCollaborationsResponseDto;
    import com.finbox.idea_collab_service.dto.reponse.EmployeeIdeasDto;
    import com.finbox.idea_collab_service.dto.reponse.IdeaColabSvcResponse;
    import com.finbox.idea_collab_service.dto.reponse.IdeaCollaborationsResponse;
    import com.finbox.idea_collab_service.dto.request.AddEmployeeRequestDto;
    import com.finbox.idea_collab_service.dto.request.CollaborationActionRequestDto;
    import com.finbox.idea_collab_service.entity.Employee;
    import com.finbox.idea_collab_service.service.EmployeeService;
    import com.finbox.idea_collab_service.service.IdeaCollaborationService;
    import com.finbox.idea_collab_service.utils.ResponseBuilder;
    import io.swagger.v3.oas.annotations.Operation;
    import jakarta.servlet.http.HttpServletRequest;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.MediaType;
    import org.springframework.http.ResponseEntity;
    import org.springframework.lang.NonNull;
    import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping(value = "")
    public class EmployeeController {

        private final EmployeeService employeeService;
        private final IdeaCollaborationService ideaCollaborationService;

        public EmployeeController(EmployeeService employeeService, IdeaCollaborationService ideaCollaborationService) {
            this.employeeService = employeeService;
            this.ideaCollaborationService = ideaCollaborationService;
        }

        @Operation(summary = "Get employee details")
        @GetMapping(value = "/employee", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<IdeaColabSvcResponse<Employee>> getEmployee(HttpServletRequest request) {
            String employeeId = (String) request.getAttribute("employeeId");
            return ResponseBuilder.build(
                    employeeService.getEmployeeById(employeeId), HttpStatus.OK);
        }

        @Operation(summary = "Onboard a new employee")
        @PostMapping(value = "/skip-auth/employee", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<IdeaColabSvcResponse<Employee>> addEmployee(
                @RequestBody AddEmployeeRequestDto addEmployeeRequestDto) {
            return ResponseBuilder.build(
                    employeeService.onboardEmployee(addEmployeeRequestDto), HttpStatus.OK);
        }

        @Operation(summary = "Get employee ideas")
        @GetMapping(value = "/employee/ideas", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<IdeaColabSvcResponse<EmployeeIdeasDto>> getEmployeeIdeas(
                @NonNull @RequestHeader String employeeId) {
            return ResponseBuilder.build(
                    employeeService.getEmployeeIdeas(employeeId), HttpStatus.OK);
        }

        @Operation(summary = "Get employee collaboration requests")
        @GetMapping(value = "/employee/idea/collaborations", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<IdeaColabSvcResponse<EmployeeCollaborationsResponseDto>> getIdeaCollaborations(HttpServletRequest request) {
            String employeeId = (String) request.getAttribute("employeeId");
            return ResponseBuilder.build(
                    ideaCollaborationService.getCollaborationRequestsByEmployeeId(employeeId), HttpStatus.OK);
        }
    }
