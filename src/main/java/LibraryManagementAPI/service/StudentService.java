
package LibraryManagementAPI.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import LibraryManagementAPI.entity.Student;
import LibraryManagementAPI.exception.DuplicateResourceException;
import LibraryManagementAPI.exception.ResourceNotFoundException;
import LibraryManagementAPI.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Add student
    public Student addStudent(Student student) {

        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }

        return studentRepository.save(student);
    }

    // Get all students
    public List<Student> getAllStudents() {

        return studentRepository.findAll();
    }

    // Get student by ID
    public Student getStudentById(int studentId) {

        return studentRepository.findById(studentId)
                .orElseThrow(() ->
                    new ResourceNotFoundException("Student not found"));
    }

    // Update student
    public Student updateStudent(int studentId, Student student) {

        Student existingStudent = getStudentById(studentId);

        if (!existingStudent.getEmail().equals(student.getEmail())
                && studentRepository.existsByEmail(student.getEmail())) {

            throw new DuplicateResourceException("Email already exists");
        }

        existingStudent.setStudentName(student.getStudentName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setDepartment(student.getDepartment());
        existingStudent.setPhoneNumber(student.getPhoneNumber());

        return studentRepository.save(existingStudent);
    }

    // Delete student
    public void deleteStudent(int studentId) {

        Student student = getStudentById(studentId);

        studentRepository.delete(student);
    }
}
