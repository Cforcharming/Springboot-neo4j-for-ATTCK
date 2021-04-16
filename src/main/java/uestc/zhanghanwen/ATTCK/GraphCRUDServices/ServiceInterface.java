package uestc.zhanghanwen.ATTCK.GraphCRUDServices;

/**
 * The interface for {@link ServiceBundle} to call and two utility methods.
 *
 * @author zhanghanwen
 * @version 1.0
 */
public interface ServiceInterface {
    /**
     * Check if node exist
     *
     * @throws Exception if fails
     * @param mitreId mitre id to check
     * @return ture if exists
     */
    Boolean isNodeExist(String mitreId) throws Exception;
    
    /**
     * Check if relationship exist
     *
     * @throws Exception if fails
     * @param startNodeMitreId start node mitre id
     * @param endNodeMitreId end node mitre id
     * @return true if exists
     */
    Boolean isRelationshipExist(String startNodeMitreId, String endNodeMitreId) throws Exception;
}
