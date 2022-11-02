package uestc.zhanghanwen.ATTCK.POJOs;

import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Id;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.JSON;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.Data;

/**
 * This is the super class for all mitre GraphNode objects.<br>
 * It also supports serialize and deserialize for both Neo4j database and {@code JSON} {@link String} <br>
 * via {@link uestc.zhanghanwen.ATTCK.Repositories.NodeRepository} and {@link JSON}. <br>
 * For details of the data inside the objects,<br>
 * please see <a href="https://github.com/mitre/cti/blob/master/USAGE.md">mitre object spec</a>
 *
 * @see uestc.zhanghanwen.ATTCK.Repositories.NodeRepository
 * @see JSON
 * @version 1.0
 * @author zhanghanwen
 */
@Data
@NoArgsConstructor
public abstract class GraphNode {
    
    /**
     * The mitre id of the object as the key.
     */
    @Id
    @JSONField(name = "mitre_id")
    @Property(name = "mitre_id")
    private String mitreId;
    
    /**
     * The name of the object.
     */
    @JSONField(name = "name")
    @Property(name = "name")
    private String name;
    
    /**
     * The description of the object.
     */
    @JSONField(name = "description")
    @Property(name = "description")
    private String description;
    
    /**
     * The platform of the object.
     */
    @JSONField(name = "platform")
    @Property(name = "platform")
    private ArrayList<String> platform;
    
    /**
     * The required permission of the object.
     */
    @JSONField(name = "permission_required")
    @Property(name = "permission_required")
    private ArrayList<String> permissionRequired;
    
    /**
     * The permission level that effects the attack.
     */
    @JSONField(name = "effective_permission")
    @Property(name = "effective_permission")
    private ArrayList<String> effectivePermission;
    
    /**
     * If the attack requires network.
     */
    @JSONField(name = "network")
    @Property(name = "network")
    private Boolean networkRequired;
    
    /**
     * If remote operations required.
     */
    @JSONField(name = "remote")
    @Property(name = "remote")
    private Boolean remoteRequired;
    
    /**
     * The requirements of the object.
     */
    @JSONField(name = "requirements")
    @Property(name = "requirements")
    private String requirements;
    
    /**
     * Serialize the object into {@link String} in the format of {@code JSON}
     *
     * @return {@code JSON string}
     */
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GraphNode graphNode = (GraphNode) o;
        return mitreId.equals(graphNode.mitreId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(mitreId);
    }
    
    /**
     * Tool method that infers the type from the mitre id.<br>
     * Note that only legal input of a mitre id will return a correct result.<br>
     * The method itself doesn't check the correctness of the input, it must be designed when the method is called.
     *
     * @param mitreId the mitre id to be inferred.
     * @return The inferred type.
     */
    public static String getTypeFromMitreId(@NotNull String mitreId) {
        
        switch (mitreId.charAt(0)) {
            case 'S':
                return "software";
                
            case 'G':
                return "group";
                
            case 'T':
                char second = mitreId.charAt(1);
                if (second == 'A') {
                    return "tactic";
                } else {
                    return "technique";
                }
                
            case 'M':
                second = mitreId.charAt(1);
                if (second == 'T') {
                    return "matrix";
                } else {
                    return "mitigation";
                }
            
            default:
                return "matrix";
        }
    }
}
