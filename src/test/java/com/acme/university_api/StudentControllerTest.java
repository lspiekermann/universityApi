package com.acme.university_api;

import com.acme.university_api.database.model.Student;
import com.acme.university_api.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StudentService yourService;  // Mocking the service layer.

    @Autowired
    private ObjectMapper objectMapper;

    @ParameterizedTest
    @CsvSource({
            ",",
            "test,''",
            "'',test",
            " , test",
            "test ,",
    })
    void testStudentValidationError(String studentName, String studentSurname) throws Exception {

        Student student = new Student();
        student.setName(studentName);
        student.setSurname(studentSurname);

        String jsonRequest = objectMapper.writeValueAsString(student);

        mockMvc.perform(post("/students/create")
                        .contentType("application/json")
                        .content(jsonRequest))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testStudentValidation() throws Exception {

        Student student = new Student();
        student.setName("Hans");
        student.setSurname("Zimmer");

        String jsonRequest = objectMapper.writeValueAsString(student);

        mockMvc.perform(post("/students/create")
                        .contentType("application/json")
                        .content(jsonRequest))
                .andExpect(status().isOk());
    }
}
