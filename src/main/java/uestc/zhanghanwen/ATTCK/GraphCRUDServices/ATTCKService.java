package uestc.zhanghanwen.ATTCK.GraphCRUDServices;

import uestc.zhanghanwen.ATTCK.Repositories.NodeRepository;
import uestc.zhanghanwen.ATTCK.Wrappers.ResultWrapper;
import uestc.zhanghanwen.ATTCK.POJOs.GraphNode;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * The abstract class for all single services.<br>
 *
 * @author zhanghanwen
 * @version 1.0
 */
@Data
@Service
public abstract class ATTCKService<GN extends GraphNode, NR extends NodeRepository<GN>> implements ServiceInterface {
    
    /**
     * The {@link NodeRepository} of the specified inherited class of {@link GraphNode}
     */
    protected NR repo;
    
    /**
     * Check if node exist
     *
     * @throws Exception if {@link NodeRepository#findByMitreId} fails
     * @param mitreId mitre id to check
     * @return ture if exists
     */
    public Boolean isNodeExist(String mitreId) throws Exception {
        return this.getRepo().findByMitreId(mitreId).size() != 0;
    }
    
    /**
     * Check if relationship exist
     *
     * @throws Exception if {@link NodeRepository#findRelationshipByMitreId} fails
     * @param startNodeMitreId start node mitre id
     * @param endNodeMitreId end node mitre id
     * @return true if exists
     */
    public Boolean isRelationshipExist(String startNodeMitreId, String endNodeMitreId) throws Exception {
        return this.getRepo().findRelationshipByMitreId(startNodeMitreId, endNodeMitreId) != null;
    }
    
    /**
     * Save one node into the graph database.
     * @param node in form of {@link JSONObject}
     * @return {@link ResultWrapper} of all queried results.
     * @throws Exception if {@link NodeRepository#save} fails or the mitre id of the saved node is not matched
     * as the expected input, then rollback.
     */
    protected ResultWrapper save(JSONObject node) throws Exception {
        
        @SuppressWarnings("unchecked")
        Class<GN> clazz = (Class<GN>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        GN graphNode = this.getRepo().save(JSONObject.toJavaObject(node, clazz));
        
        List<GN> list = new ArrayList<>();
        list.add(graphNode);
        
        return ResultWrapper.resultFromList(list);
    }
}
