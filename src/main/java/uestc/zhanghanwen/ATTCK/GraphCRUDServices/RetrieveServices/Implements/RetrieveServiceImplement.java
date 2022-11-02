package uestc.zhanghanwen.ATTCK.GraphCRUDServices.RetrieveServices.Implements;

import uestc.zhanghanwen.ATTCK.GraphCRUDServices.RetrieveServices.RetrieveService;
import uestc.zhanghanwen.ATTCK.GraphCRUDServices.ATTCKService;
import uestc.zhanghanwen.ATTCK.Repositories.NodeRepository;
import uestc.zhanghanwen.ATTCK.Wrappers.ResultWrapper;
import uestc.zhanghanwen.ATTCK.POJOs.GraphNode;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import java.util.List;

/**
 * This class has the implementation of methods in {@link RetrieveService} used in <br>
 * {@link uestc.zhanghanwen.ATTCK.GraphCRUDServices.RetrieveServices.RetrieveServiceBundle}, <br>
 *
 * @author zhanghanwen
 * @version 1.0
 */
abstract class RetrieveServiceImplement<GN extends GraphNode, NR extends NodeRepository<GN>>
        extends ATTCKService<GN, NR>
        implements RetrieveService {
    
    /**
     * Get the node by its name or mitre id.
     *
     * @throws Exception if {@link NodeRepository#findByMitreId} fails
     * @param mitreId mitre id
     * @return {@link ResultWrapper} of all queried results.
     */
    @Override
    public ResultWrapper findByMitreId(String mitreId) throws Exception {
        List<GN> list = this.getRepo().findByMitreId(mitreId);
        return ResultWrapper.resultFromList(list);
    }
    
    /**
     * Get the node by its name.
     *
     * @throws Exception if {@link NodeRepository#findByName} fails
     * @param name name
     * @return {@link ResultWrapper} of the queried result.
     */
    @Override
    public ResultWrapper findByName(String name) throws Exception {
        List<GN> list;
        list = this.getRepo().findByName(name);
        return ResultWrapper.resultFromList(list);
    }
    
    
    /**
     * Get all nodes by type, with page and size specified.<br>
     * if page is -1, then return all results.
     *
     * @throws Exception if {@link NodeRepository#findAll} fails
     * @param type the specified type.
     * @param page page, start from 0.
     * @param size size, how many records in one page.
     * @return {@link ResultWrapper} of all queried results.
     */
    @Override
    public ResultWrapper findAll(String type, int page, int size) throws Exception {

        List<GN> list;
        if (page != -1) {
            list = this.getRepo().findAll(
                    PageRequest.of(
                            page,
                            size,
                            Sort.by(Sort.DEFAULT_DIRECTION, "mitre_id")
                    )
            ).toList();
        } else {
            list = (List<GN>) this.getRepo().findAll();
        }
    
        return ResultWrapper.resultFromList(list);
    }
    
    /**
     * Find all related nodes of one node.
     *
     * @throws Exception if {@link NodeRepository#findRelatedByStartNodeMitreId} fails
     * @param startNodeMitreId mitre id of the start node.
     * @return {@link ResultWrapper} of all queried results.
     */
    @Override
    public ResultWrapper findRelatedNodes(String startNodeMitreId) throws Exception {
    
        List<GN> results = this.getRepo().findRelatedByStartNodeMitreId(startNodeMitreId);
        return ResultWrapper.resultFromList(results);
    }
    
    /**
     * Return the specified relationship name between two given nodes.
     *
     * @throws Exception if {@link NodeRepository#findRelationshipByMitreId} fails
     * @param startNodeMitreId the mitre id of the start node.
     * @param endNodeMitreId the mitre id of the end node.
     * @return the relationship name in {@link String}
     */
    @Override
    public ResultWrapper findRelationship(String startNodeMitreId, String endNodeMitreId) throws Exception {
        
        String relationship;
    
        relationship = this.getRepo().findRelationshipByMitreId(startNodeMitreId, endNodeMitreId);
    
        ResultWrapper result = new ResultWrapper();
        
        if (relationship == null) {
            return new ResultWrapper(ResultWrapper.NO_RECORD);
        }
        
        result.setStatusCode(ResultWrapper.OK);
        JSONObject name = new JSONObject(1);
        name.put("name", relationship);
        JSONArray nameArray = new JSONArray();
        nameArray.add(name);
        result.setResult(nameArray);
        
        return result;
    }
}
