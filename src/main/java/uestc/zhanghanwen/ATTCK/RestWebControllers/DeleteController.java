package uestc.zhanghanwen.ATTCK.RestWebControllers;

import uestc.zhanghanwen.ATTCK.GraphCRUDServices.DeleteServices.DeleteServiceBundle;
import uestc.zhanghanwen.ATTCK.Wrappers.ResponseWrapper;
import uestc.zhanghanwen.ATTCK.Wrappers.QueryWrapper;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.jetbrains.annotations.Contract;
import lombok.Data;

/**
 * The RESTful controller class for all creating queries.<br>
 * The root URL path for this controller is {@code domain-name/create/}. <br>
 * The services are {@link DeleteController#deleteObjectAndRelationship}
 * and {@link DeleteController#deleteRelationship}.
 *
 * @author zhanghanwen
 * @version 1.0
 */
@Data
@RestController
@RequestMapping(value = "/delete")
public class DeleteController {

    DeleteServiceBundle service;
    
    @Contract(pure = true)
    public DeleteController(DeleteServiceBundle service) {
        this.setService(service);
    }

    /**
     * Delete an object and all its relationships.
     *
     * @param id the object id to delete
     * @return true or false
     */
    @RequestMapping(value = "/node", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteObjectAndRelationship(@RequestParam(value = "id") String id) {

        if (id == null) {
            return ResponseWrapper.paramErrorResponseFactory("required: \"id\", found: none").toString();
        }

        QueryWrapper query = new QueryWrapper();
        query.setMitreId(id);

        ResponseWrapper response = this.getService().deleteNode(query);
        return response.toString();
    }

    /**
     * delete the bi-directional relationship between two nodes
     *
     * @param startNodeMitreId the object id to delete
     * @param endNodeMitreId the object id to delete
     * @return true or false
     */
    @RequestMapping(value = "/relationship", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteRelationship(
            @RequestParam(value = "start_id") String startNodeMitreId,
            @RequestParam(value = "end_id") String endNodeMitreId
    ) {

        if (startNodeMitreId == null || endNodeMitreId == null) {
            return ResponseWrapper.paramErrorResponseFactory("Required both 'start_id' and 'end_id'.").toString();
        }

        QueryWrapper query = QueryWrapper.relationshipQueryFactory(startNodeMitreId, endNodeMitreId, null);
        ResponseWrapper response = this.getService().deleteRelationship(query);

        return response.toString();
    }
}
