package uestc.zhanghanwen.ATTCK.GraphCRUDServices;

import org.springframework.stereotype.Service;
import java.util.Hashtable;
import java.util.Map;
import lombok.Getter;

/**
 * The abstract class for service bundle.
 *
 * @author zhanghanwen
 * @version 1.0
 */
@Getter
@Service
public abstract class ServiceBundle {
    
    /**
     * The hashtable containing all services of different types in {@link uestc.zhanghanwen.ATTCK.POJOs}
     */
    final protected Hashtable<String, ServiceInterface> services = new Hashtable<>(6);
    
    /**
     * Check if node exist
     *
     * @param mitreId mitre id to check
     * @return ture if exists
     */
    protected Boolean isNodeExist(String mitreId) {
        for (Map.Entry<String, ServiceInterface> entry: services.entrySet()) {
            try {
                if (entry.getValue().isNodeExist(mitreId)) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    
    /**
     * Check for two nodes' existence
     *
     * @param startNodeMitreId node 1
     * @param endNodeMitreId node 2
     * @return true if both exist
     */
    protected Boolean isStartAndEndNodeExists(String startNodeMitreId, String endNodeMitreId) {
        return this.isNodeExist(startNodeMitreId) && this.isNodeExist(endNodeMitreId);
    }
    
    /**
     * Check if relationship exist
     *
     * @param startNodeMitreId start node mitre id
     * @param endNodeMitreId end node mitre id
     * @return true if exists
     */
    protected Boolean isRelationshipExist(String startNodeMitreId, String endNodeMitreId) {
        for (Map.Entry<String, ServiceInterface> entry : services.entrySet()) {
            try {
                if (entry.getValue().isRelationshipExist(startNodeMitreId, endNodeMitreId)) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    
    /**
     * get the service of a specific type
     *
     * @param type one of {@link uestc.zhanghanwen.ATTCK.POJOs.GraphNode}
     * @return null if type not exist
     */
    protected ServiceInterface getService(String type) {
        return this.services.get(type);
    }
}
