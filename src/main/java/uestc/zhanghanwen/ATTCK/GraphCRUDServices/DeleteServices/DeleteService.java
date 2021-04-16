package uestc.zhanghanwen.ATTCK.GraphCRUDServices.DeleteServices;

import uestc.zhanghanwen.ATTCK.Wrappers.ResultWrapper;

/**
 * The interface for all Services inside.
 * This is the interface oriented mode, that makes easier for {@link DeleteServiceBundle} to manage all services.<br>
 *
 * @see DeleteServiceBundle
 * @author zhanghanwen
 * @version 1.0
 */
public interface DeleteService {

    /**
     * delete one node.
     *
     * @throws Exception if {@link uestc.zhanghanwen.ATTCK.Repositories.NodeRepository#deleteByMitreId} fails
     * @param mitreId mitre id
     * @return {@link ResultWrapper} of all queried results.
     */
    ResultWrapper deleteOne(String mitreId) throws Exception;
    
    /**
     * delete a relationship by specifying start node and end node mitre id.
     *
     * @throws Exception if {@link uestc.zhanghanwen.ATTCK.Repositories.NodeRepository#deleteRelationships} fails
     * @param startNodeMitreId mitre id
     * @param endNodeMitreId mitre id
     * @return {@link ResultWrapper} of all queried results.
     */
    ResultWrapper deleteRelationship(String startNodeMitreId, String endNodeMitreId) throws Exception;
}
