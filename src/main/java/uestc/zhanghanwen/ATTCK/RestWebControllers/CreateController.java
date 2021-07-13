package uestc.zhanghanwen.ATTCK.RestWebControllers;

import uestc.zhanghanwen.ATTCK.GraphCRUDServices.CreateServices.CreateServiceBundle;
import uestc.zhanghanwen.ATTCK.Wrappers.ResponseWrapper;
import uestc.zhanghanwen.ATTCK.Wrappers.QueryWrapper;
import org.springframework.web.bind.annotation.*;
import org.jetbrains.annotations.Contract;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * The RESTful controller class for all creating queries.<br>
 * The root URL path for this controller is {@code domain-name/create/}. <br>
 * The services are {@link CreateController#createNode} and {@link CreateController#createRelationship}.
 *
 * @author zhanghanwen
 * @version 1.0
 */
@Data
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/create", method = {RequestMethod.GET, RequestMethod.POST},
        produces="application/json;charset=UTF-8")
public class CreateController {

    CreateServiceBundle service;

    @Contract(pure = true)
    public CreateController(CreateServiceBundle service) {
        this.setService(service);
    }

    /**
     * Create a node into the database.
     *
     * @param value the object id to delete
     * @return true or false
     */
    @RequestMapping(value = "/node")
    public String createNode(@RequestParam(value = "value") String value) {

        JSONObject node = JSON.parseObject(value);
        String type = node.getString("type");
        String mitreId = node.getString("mitre_id");
        String name = node.getString("name");

        if (type == null || mitreId == null || name == null) {
            return ResponseWrapper.paramErrorResponseFactory(
                    "key \"type\", \"mitreId\" and \"name\" must be specified in \"value\", " +
                    "got: type: " + type +
                    ", mitreId: " + mitreId +
                    ", name: " + name
            ).toString();
        }

        JSONArray nodes = new JSONArray();
        nodes.add(0, node);

        QueryWrapper query = new QueryWrapper();
        query.setType(type);
        query.setNodes(nodes);

        ResponseWrapper response = this.getService().createNode(query);
        return response.toString();
    }

    /**
     * Create a relationship between two nodes.
     *
     * @param startNodeMitreId startNodeMitreId
     * @param endNodeMitreId endNodeMitreId
     * @param relationship relationship
     * @return json string
     */
    @RequestMapping(value = "/relationship")
    public String createRelationship( @RequestParam(value = "start_id") String startNodeMitreId,
                                      @RequestParam(value = "end_id") String endNodeMitreId,
                                      @RequestParam String relationship
    ) {

        if (startNodeMitreId == null && endNodeMitreId == null) {
            return ResponseWrapper.paramErrorResponseFactory("required: \"id\", found: none").toString();
        }

        QueryWrapper query = QueryWrapper.relationshipQueryFactory(startNodeMitreId, endNodeMitreId, relationship);
        ResponseWrapper response = this.getService().createRelationship(query);

        return response.toString();
    }
}
