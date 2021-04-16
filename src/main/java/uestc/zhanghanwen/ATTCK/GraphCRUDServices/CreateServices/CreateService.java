package uestc.zhanghanwen.ATTCK.GraphCRUDServices.CreateServices;

import uestc.zhanghanwen.ATTCK.Wrappers.ResultWrapper;
import com.alibaba.fastjson.JSONObject;

/**
 * The interface for all Services inside.
 * This is the interface oriented mode, that makes easier for {@link CreateServiceBundle} to manage all services.<br>
 *
 * @see CreateServiceBundle
 * @author zhanghanwen
 * @version 1.0
 */
public interface CreateService {
    
    /**
     * create one node.
     *
     * @param node in form of {@link JSONObject}
     * @throws Exception if {@link uestc.zhanghanwen.ATTCK.Repositories.NodeRepository#save} fails
     * @return {@link ResultWrapper} of all queried results.
     */
    ResultWrapper createNode(JSONObject node) throws Exception;
    
    /**
     * create a relationship by specifying start node and end node mitre id, and type.
     *
     * @throws Exception if {@link uestc.zhanghanwen.ATTCK.Repositories.NodeRepository} fails
     * @param type type of node
     * @param startNodeMitreId mitre id
     * @param endNodeMitreId mitre id
     * @param relationship relationship to create
     * @return {@link ResultWrapper} of all queried results.
     */
    ResultWrapper createRelationship(String type, String startNodeMitreId, String endNodeMitreId, String relationship)
            throws Exception;
}
