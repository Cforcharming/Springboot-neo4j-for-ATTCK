package uestc.zhanghanwen.ATTCK.RestWebControllers;

import uestc.zhanghanwen.ATTCK.GraphCRUDServices.RetrieveServices.RetrieveServiceBundle;
import uestc.zhanghanwen.ATTCK.Wrappers.ResponseWrapper;
import uestc.zhanghanwen.ATTCK.Wrappers.QueryWrapper;
import uestc.zhanghanwen.ATTCK.POJOs.GraphNode;
import org.springframework.web.bind.annotation.*;
import org.jetbrains.annotations.Contract;
import lombok.Data;

/**
 * The RESTful controller class for all retrieving queries.<br>
 * The root URL path for this controller is {@code domain-name/query/}. <br>
 * The services are {@link RetrieveController#queryObject}, {@link RetrieveController#queryType},
 * {@link RetrieveController#queryObjectByMitreId} and {@link RetrieveController#queryRelatedByStartNodeMitreId}.
 *
 * @author zhanghanwen
 * @version 1.0
 */
@Data
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/query", method = {RequestMethod.GET, RequestMethod.POST},
        produces="application/json;charset=UTF-8")
public class RetrieveController {
    
    /**
     * The bundle of services of different node types.
     * @see RetrieveServiceBundle
     */
    RetrieveServiceBundle service;

    @Contract(pure = true)
    public RetrieveController(RetrieveServiceBundle service) {
        this.setService(service);
    }

    /**
     * Query the mitre object by mitre id or name.<br>
     * The URL is {@code domain-name/query/} <br>
     * Can be accessed by both {@code GET} and {@code POST} method.<br>
     * the request parameter is {@code id} or {@code name}<br>
     * if both are provided, only id will be queried.
     *
     * @param name the name of the object to be queried.
     * @param id the mitre id to be queried.
     * @return the mitre object in JSON format.
     */
    @RequestMapping(value = "")
    public String queryObject(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String id
    ) {

        if (id != null) {
            // query by id, redirect to method RetrieveController#queryObjectByMitreId
            return this.queryObjectByMitreId(id);
        }
    
        if(name == null) {
            return ResponseWrapper.paramErrorResponseFactory("required: \"name\" or \"id\", found: none").toString();
        }
        
        //query by name
        QueryWrapper query = new QueryWrapper();
        query.setName(name);
        
        ResponseWrapper response = this.getService().findByName(query);
        return response.toString();
    }

    /**
     * Query the mitre object by mitre.<br>
     * The URL is {@code domain-name/query/{SOME_ID}/} <br>
     * Can be accessed by both {@code GET} and {@code POST} method.<br>
     *
     * @param id the mitre_id of the object to be queried.
     * @return the mitre object in JSON format.
     */
    @RequestMapping(value = "/{id}")
    public String queryObjectByMitreId(@PathVariable String id) {

        QueryWrapper query = new QueryWrapper();
        query.setMitreId(id);

        ResponseWrapper response = this.getService().findByMitreId(query);
        return response.toString();
    }


    /**
     * Query the mitre object by type.<br>
     * The URL is {@code domain-name/query/type/{SOME_TYPE}} <br>
     * Can be accessed by both {@code GET} and {@code POST} method.<br>
     * The request parameter is {@code type} {@code get_all} (optional) and {@code page} <br>
     * {@code get_all} is default {@code false} and will get a page of 20 records each time. <br>
     * When {@code get_all} is {@code false} and {@code page} is not specified, the default page is 0.
     *
     * @param isGetAll whether getting all objects or only one page, default false.
     * @param type the mitre_id of the object to be queried.
     * @param page the page number to get, default 0.
     * @param size the size of one page, default 20.
     * @return the mitre object in JSON format.
     */
    @RequestMapping(value = "/type/{type}")
    public String queryType(@PathVariable String type,
                            @RequestParam(value = "get_all", defaultValue = "false", required = false) boolean isGetAll,
                            @RequestParam(defaultValue = "0", required = false) int page,
                            @RequestParam(defaultValue = "20", required = false) int size) {

        QueryWrapper query = new QueryWrapper();
        query.setType(type);

        if (isGetAll) {
            query.setPage(-1);
            query.setSize(-1);

        } else {
            if (page >= 0) {
                query.setPage(page);
            } else {
                return ResponseWrapper.paramErrorResponseFactory("page must be no smaller than 0, got " + page)
                        .toString();
            }
            if (size > 0) {
                query.setSize(size);
            } else {
                return ResponseWrapper.paramErrorResponseFactory("size must be no smaller than 0, got " + size)
                        .toString();
            }
        }

        ResponseWrapper response = this.getService().findAll(query);
        return response.toString();
    }


    /**
     * Query all related mitre objects of one by mitre id.<br>
     * The URL is {@code domain-name/query/{SOME_ID}/related} <br>
     * Can be accessed by both {@code GET} and {@code POST} method.<br>
     * the request parameter is {@code id}
     *
     * @param id the mitre_id of the object to be queried.x
     * @return the mitre object in JSON format.
     */
    @RequestMapping(value = "{id}/related")
    public String queryRelatedByStartNodeMitreId(@PathVariable String id) {

        if (id == null) {
            return ResponseWrapper.paramErrorResponseFactory("required: \"id\", found: none").toString();
        }

        QueryWrapper query = new QueryWrapper();
        query.setMitreId(id);

        ResponseWrapper response = this.getService().findRelatedWithoutRelationshipName(query);
        return response.toString();
    }
    
    
    /**
     * Query the specified relationship name between two given nodes.<br>
     * The URL is {@code domain-name/query/related} <br>
     * Can be accessed by both {@code GET} and {@code POST} method.<br>
     * the request parameter is {@code start_id} and {@code end_id}
     *
     * @param startNodeMitreId the mitre id of the start node.
     * @param endNodeMitreId the mitre id of the end node.
     * @return the relationship name
     */
    @RequestMapping(value = "/related")
    public String queryRelationshipByStartAndEndNodeMitreId(@RequestParam(value = "start_id") String startNodeMitreId,
                                                            @RequestParam(value = "end_id") String endNodeMitreId) {
        
        if (startNodeMitreId == null || endNodeMitreId == null) {
            return ResponseWrapper.paramErrorResponseFactory("both \"start_id\" and \"end_id\" parameter required")
                    .toString();
        }
        
        QueryWrapper query = QueryWrapper.relationshipQueryFactory(startNodeMitreId, endNodeMitreId, null);
        query.setType(GraphNode.getTypeFromMitreId(startNodeMitreId));
        
        ResponseWrapper response = this.getService().findRelationship(query);
        return response.toString();
    }
}
