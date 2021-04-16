package uestc.zhanghanwen.ATTCK.RestWebControllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.containsString;
import org.junit.jupiter.api.Test;

@SpringBootTest
@AutoConfigureMockMvc
class DeleteControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    void deleteNode() throws Exception {
        
        this.mockMvc.perform(get("/delete/node?id=A"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"status\":0")));
    }
    
    @Test
    void deleteRelationships() throws Exception {
        
        this.mockMvc.perform(get("/delete/relationship?start_id=A&end_id=B"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"status\":0")));
    }
}