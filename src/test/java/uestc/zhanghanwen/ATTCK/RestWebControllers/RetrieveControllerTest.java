package uestc.zhanghanwen.ATTCK.RestWebControllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.containsString;
import org.junit.jupiter.api.Test;

/**
 * Test Class for {@link RetrieveController}.
 *
 * @author zhanghanwen
 * @version 1.0
 */
@SpringBootTest
@AutoConfigureMockMvc
class RetrieveControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    void test404() throws Exception {
        this.mockMvc.perform(get("/queries"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    
    @Test
    void queryObject() throws Exception {
        this.mockMvc.perform(get("/query?name=pre"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"status\":0")));
    }
    
    @Test
    void queryObjectWithBlank() throws Exception {
        this.mockMvc.perform(post("/query").param("name", "Initial Access"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    
    @Test
    void queryObjectNotFound() throws Exception {
        this.mockMvc.perform(get("/query?name=pr"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"status\":3")));
    }
    
    @Test
    void queryByMitreIdParameterRedirect() throws Exception {
        this.mockMvc.perform(get("/query?id=MT0001"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"status\":0")));
    }
    
    @Test
    void queryObjectByMitreId() throws Exception {
        this.mockMvc.perform(get("/query/TA0001"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"status\":0")));
    }
    
    @Test
    void queryObjectByMitreIdTypeNOTfound() throws Exception {
        this.mockMvc.perform(get("/query/A0001"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"status\":3")));
    }
    
    @Test
    void queryType() throws Exception {
        this.mockMvc.perform(get("/query/type/technique?page=5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"status\":0")));
    }
    
    @Test
    void queryTypeGetAll() throws Exception {
        this.mockMvc.perform(get("/query/type/matrix?get_all=true"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"status\":0")));
    }
    
    @Test
    void queryTypeNotFound() throws Exception {
        this.mockMvc.perform(get("/query/type/g"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"status\":3")));
    }
    
    @Test
    void queryRelatedByMitreId() throws Exception {
        this.mockMvc.perform(get("/query/MT0001/related"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"status\":0")));
    }
    
    @Test
    void queryRelatedByMitreIdNotFound() throws Exception {
        this.mockMvc.perform(get("/query/MT000/related"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"status\":3")));
    }
    
    @Test
    void queryRelationshipNotFound() throws Exception {
        this.mockMvc.perform(get("/query/related?start_id=MT0001&end_id=TA0001"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"status\":3")));
    }
    
    @Test
    void queryRelationship() throws Exception {
        this.mockMvc.perform(get("/query/related?start_id=MT0002&end_id=TA0001"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"status\":0")));
    }
    
    @Test
    void queryRelationshipNotExist() throws Exception {
        this.mockMvc.perform(get("/query/related?start_id=MT000&end_id=TA0001"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"status\":3")));
    }
}