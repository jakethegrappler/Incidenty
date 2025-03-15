package cz.cvut.fel.incidenty.rest.controller;

import cz.cvut.fel.incidenty.dto.StudentDto;
import cz.cvut.fel.incidenty.model.Student;
import cz.cvut.fel.incidenty.service.StudentService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/student")
@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> createStudent(@RequestBody StudentDto studentDto) {
        return ResponseEntity.ok(studentService.createStudent(studentDto));
    }
}
