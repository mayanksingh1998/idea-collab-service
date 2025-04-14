package com.finbox.idea_collab_service.controller;

import com.finbox.idea_collab_service.dto.reponse.EmployeeIdeasDto;
import com.finbox.idea_collab_service.dto.reponse.IdeaColabSvcResponse;
import com.finbox.idea_collab_service.dto.request.AddEmployeeRequestDto;
import com.finbox.idea_collab_service.entity.Employee;
import com.finbox.idea_collab_service.service.EmployeeService;
import com.finbox.idea_collab_service.utils.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdeaColabSvcResponse<Employee>> getEmployee(HttpServletRequest request) {
        String employeeId = (String) request.getAttribute("employeeId");
        return ResponseBuilder.build(
                employeeService.getEmployeeById(employeeId), HttpStatus.OK);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdeaColabSvcResponse<Employee>> addEmployee(
            @RequestBody AddEmployeeRequestDto addEmployeeRequestDto) {
        return ResponseBuilder.build(
                employeeService.onboardEmployee(addEmployeeRequestDto), HttpStatus.OK);
    }

    @GetMapping(value = "/ideas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdeaColabSvcResponse<EmployeeIdeasDto>> getEmployeeIdeas(
            @NonNull @RequestHeader String employeeId) {
        return ResponseBuilder.build(
                employeeService.getEmployeeIdeas(employeeId), HttpStatus.OK);
    }
}
