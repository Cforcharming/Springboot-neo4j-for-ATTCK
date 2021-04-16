package uestc.zhanghanwen.ATTCK.Wrappers;

import uestc.zhanghanwen.ATTCK.POJOs.GraphNode;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import lombok.NoArgsConstructor;
import lombok.Data;

/**
 * The encapsulation of different types of queries of all REST requests of CURD requests.
 *
 * @author zhanghanwen
 * @version 1.0
 */
@Data
@NoArgsConstructor
public class QueryWrapper {
    
    /**
     * The mitre id of the object to be queried.
     */
    private String mitreId;
    
    /**
     * The name of the object to be queried.
     */
    private String name;
    
    /**
     * The type to be queried.
     */
    private String type;
    
    /**
     * Is all results are got, else paged by {@link QueryWrapper#page} and {@link QueryWrapper#size}.
     */
    private boolean isGetAll;
    
    /**
     * If {@link QueryWrapper#isGetAll} is {@code false}, set the page.
     */
    private int page;
    
    /**
     * If {@link QueryWrapper#isGetAll} is {@code false}, set the size of the page.
     */
    private int size;
    
    /**
     * In the request of a relationship, two objects of serialized {@link GraphNode} can be put in.
     */
    private JSONArray nodes;
    
    /**
     * What relationship to be queried.
     */
    private String relationship;

    /**
     * Factory mode for generating {@link QueryWrapper} of querying relationship.
     *
     * @param startNodeMitreId The start node mitre id.
     * @param endNodeMitreId The end node mitre id.
     * @param relationship can be {@code null} if the above two are given.
     * @return Generated {@link QueryWrapper}
     */
    public static QueryWrapper relationshipQueryFactory(String startNodeMitreId,
                                                        String endNodeMitreId,
                                                        String relationship) {

        QueryWrapper query = new QueryWrapper();

        JSONObject startNode = new JSONObject();
        startNode.put("mitre_id", startNodeMitreId);
        JSONObject endNode = new JSONObject();
        endNode.put("mitre_id", endNodeMitreId);
    
        query.setType(GraphNode.getTypeFromMitreId(startNodeMitreId));

        JSONArray nodes = new JSONArray();
        nodes.add(0, startNode);
        nodes.add(1, endNode);

        query.setNodes(nodes);
        query.setRelationship(relationship);
        return query;
    }
    
    /**
     * Setter method for node.<br>
     * It also sets the type automatically by calling {@link GraphNode#getTypeFromMitreId}
     *
     * @param mitreId the id to be set.
     */
     public void setMitreId(String mitreId) {
        this.mitreId = mitreId;
        this.type = GraphNode.getTypeFromMitreId(mitreId);
    }
}
