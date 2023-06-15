package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private  final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }
    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) throws IllegalAccessException {
        Optional<Student> studentOptional = studentRepository
                .findStudentByEmail(student.getEmail());
        System.out.println(student);
        if(studentOptional.isPresent()){
            throw new IllegalAccessException("Email Taken!");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) throws IllegalAccessException {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalAccessException("This student doesn't exist!");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Student student) throws IllegalAccessException {
        boolean exists = studentRepository.existsById(student.getId());
        if(!exists){
            throw new IllegalAccessException("This student doesn't exist");
        }
        Optional<Student> student1 = studentRepository.findById(student.getId());
        if(student.getName() != student1.get().getName() || student.getEmail()!=student1.get().getName()){
        studentRepository.save(student);
        }
    }
}
