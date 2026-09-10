package com.lseg.studentservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable int id){
        return new Student(id,"JohnDoe","Johndoe@gmail.com","A");
    }


    @GetMapping("/{id}/with-payment")
    public Map<String, Object> getStudentWithPayment(@PathVariable int id){
        Student student = new Student(id,"JohnDoe","Johndoe@gmail.com","A");

        try {
            String paymentUrl = "http://localhost:8585/payments/" + id;
            Object payment = restTemplate.getForObject(paymentUrl, Object.class);

            Map<String, Object> response = new LinkedHashMap<>();
            response.put("student", student);
            response.put("payment", payment);
            return response;
        } catch (Exception e) {
            Map<String, Object> response = new LinkedHashMap<>();
            response.put("student", student);
            response.put("payment", "Provider service unavailable");
            return response;
        }
    }
}