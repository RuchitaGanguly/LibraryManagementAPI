
package LibraryManagementAPI.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import LibraryManagementAPI.entity.Student;
import LibraryManagementAPI.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Add student
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student addStudent(@Valid @RequestBody Student student) {

        return studentService.addStudent(student);
    }

    // Get all students
    @GetMapping
    public List<Student> getAllStudents() {

        return studentService.getAllStudents();
    }

    // Get student by ID
    @GetMapping("/{studentId}")
    public Student getStudentById(@PathVariable int studentId) {

        return studentService.getStudentById(studentId);
    }

    // Update student
    @PutMapping("/{studentId}")
    public Student updateStudent(@PathVariable int studentId,
    		@Valid @RequestBody Student student) {

        return studentService.updateStudent(studentId, student);
    }

    // Delete student
    @DeleteMapping("/{studentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable int studentId) {

        studentService.deleteStudent(studentId);
    }
}
