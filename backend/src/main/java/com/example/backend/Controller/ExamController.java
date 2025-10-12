package com.example.backend.Controller;

import com.example.backend.Model.Exam;
import com.example.backend.Service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/exams")
@CrossOrigin(origins = { "http://localhost:4200", "http://192.168.0.107:4200" }, 
             allowedHeaders = "*", allowCredentials = "true")
public class ExamController {

    @Autowired
    private ExamService examService;

    // Tạo lịch thi mới
    @PostMapping("/class/{classId}")
    public ResponseEntity<Map<String, Object>> createExam(@PathVariable Long classId,
                                                         @RequestBody Exam exam,
                                                         @RequestHeader("User-ID") Long teacherId) {
        try {
            Exam createdExam = examService.createExam(exam, classId, teacherId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Tạo lịch thi thành công");
            response.put("status", "success");
            response.put("data", createdExam);
            return ResponseEntity.ok(response);
            
        } catch (RuntimeException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", e.getMessage());
            response.put("status", "error");
            return ResponseEntity.badRequest().body(response);
        }
    }

    // Cập nhật lịch thi
    @PutMapping("/{examId}")
    public ResponseEntity<Map<String, Object>> updateExam(@PathVariable Long examId,
                                                         @RequestBody Exam examDetails,
                                                         @RequestHeader("User-ID") Long teacherId) {
        try {
            Exam updatedExam = examService.updateExam(examId, examDetails, teacherId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Cập nhật lịch thi thành công");
            response.put("status", "success");
            response.put("data", updatedExam);
            return ResponseEntity.ok(response);
            
        } catch (RuntimeException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", e.getMessage());
            response.put("status", "error");
            return ResponseEntity.badRequest().body(response);
        }
    }

    // Xóa lịch thi
    @DeleteMapping("/{examId}")
    public ResponseEntity<Map<String, Object>> deleteExam(@PathVariable Long examId,
                                                         @RequestHeader("User-ID") Long teacherId) {
        try {
            examService.deleteExam(examId, teacherId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Xóa lịch thi thành công");
            response.put("status", "success");
            return ResponseEntity.ok(response);
            
        } catch (RuntimeException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", e.getMessage());
            response.put("status", "error");
            return ResponseEntity.badRequest().body(response);
        }
    }

    // Lấy lịch thi theo lớp
    @GetMapping("/class/{classId}")
    public ResponseEntity<Map<String, Object>> getExamsByClass(@PathVariable Long classId) {
        try {
            List<Exam> exams = examService.getExamsByClass(classId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Lấy lịch thi thành công");
            response.put("status", "success");
            response.put("data", exams);
            return ResponseEntity.ok(response);
            
        } catch (RuntimeException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", e.getMessage());
            response.put("status", "error");
            return ResponseEntity.badRequest().body(response);
        }
    }

    // Lấy lịch thi của giáo viên
    @GetMapping("/teacher")
    public ResponseEntity<Map<String, Object>> getTeacherExams(@RequestHeader("User-ID") Long teacherId) {
        try {
            List<Exam> exams = examService.getExamsByTeacher(teacherId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Lấy lịch thi thành công");
            response.put("status", "success");
            response.put("data", exams);
            return ResponseEntity.ok(response);
            
        } catch (RuntimeException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", e.getMessage());
            response.put("status", "error");
            return ResponseEntity.badRequest().body(response);
        }
    }
}