package uestc.zhanghanwen.ATTCK.GraphCRUDServices.RetrieveServices;

import uestc.zhanghanwen.ATTCK.Wrappers.ResultWrapper;

/**
 * The interface for all Services inside.
 * This is the interface oriented mode, that makes easier for {@link RetrieveServiceBundle}
 * to manage all services.<br>
 *
 * @see RetrieveServiceBundle
 * @author zhanghanwen
 * @version 1.0
 */
public interface RetrieveService {
    
    /**
     * Get the node by its mitre id.
     *
     * @throws Exception if {@link uestc.zhanghanwen.ATTCK.Repositories.NodeRepository#findByMitreId} fails
     * @param mitreId mitre id
     * @return {@link ResultWrapper} of the queried result.
     */
    ResultWrapper findByMitreId(String mitreId) throws Exception;
    
    /**
     * Get the node by its name.
     *
     * @throws Exception if {@link uestc.zhanghanwen.ATTCK.Repositories.NodeRepository#findByName} fails
     * @param name name
     * @return {@link ResultWrapper} of the queried result.
     */
    ResultWrapper findByName(String name) throws Exception;

    /**
     * Get all nodes by type, with page and size specified.<br>
     * if page is both -1, then return all results.
     *
     * @throws Exception if {@link uestc.zhanghanwen.ATTCK.Repositories.NodeRepository#findAll} fails
     * @param type the specified type.
     * @param page page, start from 0.
     * @param size size, how many records in one page.
     * @return {@link ResultWrapper} of all queried results.
     */
    ResultWrapper findAll(String type, int page, int size) throws Exception;

    /**
     * Find all related nodes of one node.
     *
     * @throws Exception
     * if {@link uestc.zhanghanwen.ATTCK.Repositories.NodeRepository#findRelatedByStartNodeMitreId} fails
     * @param startNodeMitreId mitre id of the start node.
     * @return {@link ResultWrapper} of all queried results.
     */
    ResultWrapper findRelatedNodes(String startNodeMitreId) throws Exception;
    
    /**
     * Return the specified relationship name between two given nodes.
     *
     * @throws Exception if {@link uestc.zhanghanwen.ATTCK.Repositories.NodeRepository#findRelationshipByMitreId} fails
     * @param startNodeMitreId the mitre id of the start node.
     * @param endNodeMitreId the mitre id of the end node.
     * @return {@link ResultWrapper} containing the relationship name.
     */
    ResultWrapper findRelationship(String startNodeMitreId, String endNodeMitreId) throws Exception;
}
