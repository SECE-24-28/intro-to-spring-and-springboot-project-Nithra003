package com.eduhub.eduhub_backend.controller;

import com.eduhub.eduhub_backend.component.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {


    @GetMapping("student")

    public ResponseEntity<Student> getStudent(){
        Student student = new Student( 1,"ram" ,"ks");
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("students")

    public ResponseEntity<List<Student>>getStudents(){
       List<Student> studentList = new ArrayList<>();
       studentList.add(new Student(1,"Nithra","P"));
        studentList.add(new Student(1,"Pavi","B"));
        studentList.add(new Student(1,"Selva","R"));
        studentList.add(new Student(1,"Manisha","R"));

        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    ///http://localhost:8080/pavi/s
    @GetMapping("{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id")int studentId,@PathVariable("fisrt-name") String firstName,@PathVariable("last-name") String lastName)
    {
        Student student=new Student(studentId,firstName,lastName);
       //return new ResponseEntity<>(student, HttpStatus.OK);
        return  ResponseEntity.ok(student);
    }

    //http://localhost:8080/query?studentId=1&firstName=pavi&lastName=r
@GetMapping("query")
    public ResponseEntity<Student> studentRequestEntity(@RequestParam int studentId,@RequestParam String firstName,@RequestParam String lastName) {
    Student student = new Student(studentId, firstName, lastName);
    return ResponseEntity.ok(student);
}

//http://localhost:8080/create
/*{
    "id": 7,
        "firstName": "nithra",
        "lastName": "p"
}*/
@PostMapping("create")
public ResponseEntity<Student> createStudent(@RequestBody Student student) {

    System.out.println(student.getId());
    System.out.println(student.getFirstName());
    System.out.println(student.getLastName());

    return ResponseEntity.ok(student);
}

@PutMapping("update")
    public ResponseEntity updateStudent(@PathVariable("id") int studentId,@RequestBody Student student){
    return ResponseEntity.accepted().body(student);
}

    @DeleteMapping("{id}/delete")
    public ResponseEntity updateStudent(@PathVariable("id") int studentId){
        return ResponseEntity.accepted().body("Data removed successfully");
    }
}
