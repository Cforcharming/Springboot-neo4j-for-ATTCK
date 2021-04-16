package uestc.zhanghanwen.ATTCK.GraphCRUDServices.DeleteServices.Implements;

import uestc.zhanghanwen.ATTCK.GraphCRUDServices.DeleteServices.DeleteService;
import uestc.zhanghanwen.ATTCK.GraphCRUDServices.ATTCKService;
import uestc.zhanghanwen.ATTCK.Repositories.NodeRepository;
import uestc.zhanghanwen.ATTCK.Wrappers.ResultWrapper;
import uestc.zhanghanwen.ATTCK.POJOs.GraphNode;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSONArray;

/**
 * This class has the implementation of methods, <br>
 *
 * @author zhanghanwen
 * @version 1.0
 */
abstract class DeleteServiceImplement<GN extends GraphNode, NR extends NodeRepository<GN>>
        extends ATTCKService<GN, NR>
        implements DeleteService {

    /**
     * delete one node.
     *
     * @param mitreId mitre id
     * @throws Exception if {@link NodeRepository#save} fails or the mitre id of the saved node is not matched
     * as the expected input, then rollback.
     * @return {@link JSONArray} of all queried results.
     */
    @Override
    @Transactional
    public ResultWrapper deleteOne(String mitreId) throws Exception {
        
        this.getRepo().deleteRelationships(mitreId);
        this.getRepo().deleteByMitreId(mitreId);

        ResultWrapper result = new ResultWrapper(ResultWrapper.OK);
        result.setMsgSpec("Deleted node: '" + mitreId + "'.");
        result.setResult(new JSONArray());
        
        return result;
    }
    
    /**
     * delete a relationship by specifying start node and end node mitre id.
     *
     * @param startNodeMitreId mitre id
     * @param endNodeMitreId   mitre id
     * @throws Exception if {@link NodeRepository#save} fails or the mitre id of the saved node is not matched
     * as the expected input, then rollback.
     * @return {@link JSONArray} of all queried results.
     */
    @Override
    @Transactional
    public ResultWrapper deleteRelationship(String startNodeMitreId, String endNodeMitreId) throws Exception {
        
        this.getRepo().deleteRelationshipByStartNodeMitreId(startNodeMitreId, endNodeMitreId);
        
        ResultWrapper result = new ResultWrapper(ResultWrapper.OK);
        result.setMsgSpec("Relationship deleted.");
        result.setResult(new JSONArray());
        
        return result;
    }
}
