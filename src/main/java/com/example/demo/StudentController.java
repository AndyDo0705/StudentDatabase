package com.example.demo;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class StudentController {
    @Autowired
    private StudentRepository userRepo;

    @PostMapping("/students/add")
    public String addStudents(@RequestParam Map<String, String> newStudent, HttpServletResponse response) {
        String newStudents = newStudent.get("name");
        Float newWeight = Float.parseFloat(newStudent.get("weight"));
        Float newGPA = Float.parseFloat(newStudent.get("gpa"));
        String newHairColor = newStudent.get("haircolor");
        int newHeight = Integer.parseInt(newStudent.get("height"));
        userRepo.save(new Student(newStudents, newWeight, newHairColor, newGPA, newHeight));

        response.setStatus(201);
        return "redirect:/students";
    }

    @GetMapping("/students")
    public String showStudents(Model model) {
        List<Student> students = userRepo.findAll();
        model.addAttribute("stu", students);
        return "users/showAll";
    }

    @PostMapping("/students/{uid}/delete")
    public String deleteStudent(@PathVariable("uid") Integer uid) {
        System.out.println("Deleting student");

        Optional<Student> studentOp = userRepo.findById(uid);
        if (studentOp.isPresent()) {
            userRepo.deleteById(uid);
            return "redirect:/students";
        } else {
            return "users-error";
        }
    }

    @PostMapping("/students/{uid}/update")
    public String updateStudent(@PathVariable("uid") Integer uid, @RequestParam Map<String, String> newStudent,
            HttpServletResponse response) {

        Student student = userRepo.findById(uid).orElseThrow(() -> new IllegalArgumentException("Invalid student ID"));

        String newStudents = newStudent.get("name");
        Float newWeight = Float.parseFloat(newStudent.get("weight"));
        Float newGPA = Float.parseFloat(newStudent.get("gpa"));
        String newHairColor = newStudent.get("haircolor");
        int newHeight = Integer.parseInt(newStudent.get("height"));

        // Update the student with the new data
        student.setName(newStudents);
        student.setWeight(newWeight);
        student.setGpa(newGPA);
        student.setHairColor(newHairColor);
        student.setHeight(newHeight);

        // Save the updated student to the database
        userRepo.save(student);

        return "redirect:/students";
    }
}