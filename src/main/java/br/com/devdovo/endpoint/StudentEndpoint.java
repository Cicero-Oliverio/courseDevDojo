package br.com.devdovo.endpoint;

import static java.util.Arrays.asList;

import br.com.devdovo.error.CustomErrorType;
import br.com.devdovo.model.Student;
import br.com.devdovo.util.DateUtil;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("student")
public class    StudentEndpoint {

    private final DateUtil dateUtil;

    @Autowired
    public StudentEndpoint(DateUtil dateUtil) {
        this.dateUtil = dateUtil;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> listAll(){

        dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now());
        return new ResponseEntity<>(Student.studentList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET,path = "/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") int id){
        Student student = new Student();
        student.setId(id);
        int index = Student.studentList.indexOf(student);
        if(index == -1)
            return new ResponseEntity<>(new CustomErrorType("Student not Found"),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(Student.studentList.get(index),HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Student student){

        Student.studentList.add(student);
        return new ResponseEntity<>(student,HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@RequestBody Student student){

        Student.studentList.remove(student);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody Student student){

        Student.studentList.remove(student);
        Student.studentList.add(student);
        return new ResponseEntity<>(HttpStatus.OK);

    }


}
