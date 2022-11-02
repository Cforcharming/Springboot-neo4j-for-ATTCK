package uestc.zhanghanwen.ATTCK.GraphCRUDServices.CreateServices.Implements;

import uestc.zhanghanwen.ATTCK.GraphCRUDServices.CreateServices.CreateService;
import uestc.zhanghanwen.ATTCK.GraphCRUDServices.ATTCKService;
import uestc.zhanghanwen.ATTCK.Repositories.NodeRepository;
import uestc.zhanghanwen.ATTCK.Wrappers.ResultWrapper;
import uestc.zhanghanwen.ATTCK.POJOs.GraphNode;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;

/**
 * This class has the implementation of methods in {@link CreateService} used in <br>
 * {@link uestc.zhanghanwen.ATTCK.GraphCRUDServices.CreateServices.CreateServiceBundle}.
 *
 * @author zhanghanwen
 * @version 1.0
 */
abstract class CreateServiceImplement<GN extends GraphNode, NR extends NodeRepository<GN>>
        extends ATTCKService<GN, NR>
        implements CreateService {
    
    /**
     * create one node using the {@link CreateServiceImplement#repo}.
     *
     * @param node node
     * @return {@link ResultWrapper} of all queried results.
     * @throws Exception if {@link NodeRepository#save} fails or the mitre id of the saved node is not matched
     * as the expected input, then rollback.
     * @see ATTCKService#save
     */
    @Override
    @Transactional
    public ResultWrapper createNode(JSONObject node) throws Exception {
        return this.save(node);
    }

    /**
     * create a relationship by specifying start node and end node mitre id, and type
     * using the {@link CreateServiceImplement#repo}.
     *
     * @param type one of {@link uestc.zhanghanwen.ATTCK.POJOs}
     * @param startNodeMitreId start node mitre id
     * @param endNodeMitreId end node mitre id
     * @return {@link JSONArray} of all queried results.
     * @throws Exception if {@link NodeRepository#save} fails or the mitre id of the saved node is not matched
     * as the expected input, then rollback.
     */
    @Override
    @Transactional
    public ResultWrapper createRelationship(String type,
                                            String startNodeMitreId,
                                            String endNodeMitreId,
                                            String relationship) throws Exception {
        
        ResultWrapper result;
    
        switch (relationship) {
            case "contains":
                this.getRepo().createContainsRelationshipByMitreId(startNodeMitreId, endNodeMitreId);
                break;
                
            case "in":
                this.getRepo().createInRelationshipByMitreId(startNodeMitreId, endNodeMitreId);
                break;
                
            case "uses":
                this.getRepo().createUsesRelationshipByMitreId(startNodeMitreId, endNodeMitreId);
                break;
                
            case "is used by":
                this.getRepo().createUsedByRelationshipByMitreId(startNodeMitreId, endNodeMitreId);
                break;
                
            default:
                result = new ResultWrapper(ResultWrapper.REQUEST_ERROR);
                result.setMsgSpec("Unable to create relationship, because cannot find a relationship of type "
                        + relationship);
                return result;
        }
        
        result = new ResultWrapper(ResultWrapper.OK);
        result.setMsgSpec("created relationship: '" + startNodeMitreId + "' "
                + relationship + " '" + endNodeMitreId + "'.");
        result.setResult(new JSONArray(0));
    
        return result;
    }
}
