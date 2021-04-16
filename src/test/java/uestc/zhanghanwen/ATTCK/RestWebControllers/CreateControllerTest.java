package uestc.zhanghanwen.ATTCK.RestWebControllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.hamcrest.Matchers.containsString;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

@SpringBootTest
@AutoConfigureMockMvc
class CreateControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    void createNode() throws Exception {
    
        JSONObject toCreate = new JSONObject();
        
        toCreate.put("name", "test1");
        toCreate.put("mitre_id", "A");
        toCreate.put("type", "matrix");
        
        this.mockMvc.perform(get("/create/node").param("value", toCreate.toJSONString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"status\":0")));
        
        toCreate = new JSONObject();
    
        toCreate.put("name", "test2");
        toCreate.put("mitre_id", "B");
        toCreate.put("type", "matrix");
    
        this.mockMvc.perform(get("/create/node").param("value", toCreate.toJSONString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"status\":0")));
    }
    
    @Test
    void createNodeDuplicate() throws Exception {
        JSONObject toCreate = new JSONObject();
    
        toCreate.put("name", "test1");
        toCreate.put("mitre_id", "A");
        toCreate.put("type", "matrix");
    
        this.mockMvc.perform(get("/create/node").param("value", toCreate.toJSONString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"status\":4")));
    }
    
    @Test
    void doNothing() throws Exception {
        this.mockMvc.perform(get("/create"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    
    @Test
    void createRelationship() throws Exception {
        this.mockMvc.perform(get("/create/relationship?start_id=A&end_id=B&relationship=in"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"status\":0")));
    }
    
    @Test
    void createRelationshipDuplicate() throws Exception {
        this.mockMvc.perform(get("/create/relationship?start_id=A&end_id=B&relationship=in"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"status\":4")));
    }
    
    @Test
    void createWrongRelationship() throws Exception {
        this.mockMvc.perform(get("/create/relationship?start_id=A&end_id=B&relationship=out"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"status\":1")));
    }
    
    @Test
    void createWrongRelationshipNotExist() throws Exception {
        this.mockMvc.perform(get("/create/relationship?start_id=C&end_id=B&relationship=in"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"status\":3")));
    }
}
