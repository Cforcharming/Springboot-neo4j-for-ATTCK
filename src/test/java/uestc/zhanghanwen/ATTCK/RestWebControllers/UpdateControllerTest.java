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
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

@SpringBootTest
@AutoConfigureMockMvc
class UpdateControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    void updateNode() throws Exception {
        
        JSONObject toCreate = new JSONObject();
        
        toCreate.put("name", "test0");
        toCreate.put("mitre_id", "A");
        toCreate.put("type", "matrix");
        
        this.mockMvc.perform(get("/update").param("value", toCreate.toJSONString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"status\":0")));
        
        toCreate = new JSONObject();
        
        toCreate.put("name", "test1");
        toCreate.put("mitre_id", "B");
        toCreate.put("type", "matrix");
        
        this.mockMvc.perform(get("/update").param("value", toCreate.toJSONString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"status\":0")));
    }
    
    @Test
    void updateNodeDoNotExist() throws Exception {
        
        JSONObject toCreate = new JSONObject();
        
        toCreate.put("name", "test0");
        toCreate.put("mitre_id", "C");
        toCreate.put("type", "matrix");
        
        this.mockMvc.perform(get("/update").param("value", toCreate.toJSONString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"status\":3")));
    }
}