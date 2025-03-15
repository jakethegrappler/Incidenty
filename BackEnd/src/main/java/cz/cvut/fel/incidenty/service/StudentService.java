package cz.cvut.fel.incidenty.service;

import cz.cvut.fel.incidenty.dto.StudentDto;
import cz.cvut.fel.incidenty.mapper.StudentMapper;
import cz.cvut.fel.incidenty.model.Student;
import cz.cvut.fel.incidenty.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public Student createStudent(StudentDto studentDto) {
        Student student = studentMapper.toEntity(studentDto);
        return studentRepository.save(student);
    }
}
