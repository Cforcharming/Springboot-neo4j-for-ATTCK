package uestc.zhanghanwen.ATTCK.RestWebControllers;

import uestc.zhanghanwen.ATTCK.GraphCRUDServices.UpdateServices.UpdateServiceBundle;
import uestc.zhanghanwen.ATTCK.Wrappers.ResponseWrapper;
import uestc.zhanghanwen.ATTCK.Wrappers.QueryWrapper;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSON;
import org.jetbrains.annotations.Contract;
import lombok.Data;

/**
 * The RESTful controller class for all creating queries.<br>
 * The root URL path for this controller is {@code domain-name/create/}. <br>
 * The services are {@link UpdateController#updateNode}.
 *
 * @author zhanghanwen
 * @version 1.0
 */
@Data
@RestController
@RequestMapping(value = "/update")
public class UpdateController {

    UpdateServiceBundle service;
    
    @Contract(pure = true)
    public UpdateController(UpdateServiceBundle service) {
        this.setService(service);
    }

    /**
     * Update an existing node in the database.
     *
     * @param value the object id to delete
     * @return true or false
     */
    @RequestMapping(value = "", method = {RequestMethod.GET, RequestMethod.POST})
    public String updateNode(@RequestParam(value = "value") String value) {

        JSONObject node = JSON.parseObject(value);
        JSONArray nodes = new JSONArray();
        nodes.add(0, node);

        QueryWrapper request = new QueryWrapper();
        request.setNodes(nodes);

        ResponseWrapper response = this.getService().mergeNode(request);
        return response.toString();
    }
}