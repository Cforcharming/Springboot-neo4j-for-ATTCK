package uestc.zhanghanwen.ATTCK.Repositories;

import uestc.zhanghanwen.ATTCK.POJOs.GraphNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * The {@link Neo4jRepository} interface of cypher queries.<br>
 * For different types of node, each one can inherit to create its own repository.
 *
 * @see Neo4jRepository
 * @see GraphNode
 * @author zhanghanwen
 * @version 1.0
 * @param <GN> inheritance of {@link GraphNode}
 */
@Repository
public interface NodeRepository<GN extends GraphNode> extends Neo4jRepository<GN, String> {
    
    /**
     * query object by name from database
     *
     * @param name name
     * @return List of queried objects
     * @throws Exception if fails
     */
    @Query("MATCH (m) WHERE m.name=$name RETURN m;")
    List<GN> findByName(@Param("name") String name) throws Exception;
    
    /**
     * query object by id from database
     *
     * @param id id
     * @return List of queried objects
     * @throws Exception if fails
     */
    @Query("MATCH (m) WHERE m.mitre_id=$id RETURN m;")
    List<GN> findByMitreId(@Param("id") String id) throws Exception;
    
    /**
     * query all related objects from database
     *
     * @param mitreId id
     * @param <EN> instance of {@link GraphNode}
     * @return List of queried objects
     * @throws Exception if fails
     */
    @Query("MATCH (m)-[r]->(n) WHERE m.mitre_id=$m1 RETURN n;")
    <EN extends GraphNode> List<EN> findRelatedByStartNodeMitreId(@Param("m1") String mitreId) throws Exception;
    
    /**
     * query relationship between two objects by id from database
     *
     * @param mitreId1 start node
     * @param mitreId2 end node
     * @return List of queried objects
     * @throws Exception if fails
     */
    @Query("MATCH (m)-[r]->(n) WHERE m.mitre_id=$m1 AND n.mitre_id=$m2 RETURN type(r);")
    String findRelationshipByMitreId(@Param("m1") String mitreId1, @Param("m2") String mitreId2) throws Exception;
    
    /**
     * create relationship 'contains' between two objects by id into database
     *
     * @param mitreId1 start node
     * @param mitreId2 end node
     * @throws Exception if fails
     */
    @Query("MATCH (m), (n) WHERE m.mitre_id=$m1 AND n.mitre_id=$m2 MERGE (m)-[r:contains]->(n) MERGE (n)-[re:in]->(m);")
    void createContainsRelationshipByMitreId(@Param("m1") String mitreId1, @Param("m2") String mitreId2)
            throws Exception;
    /**
     * create relationship 'in' between two objects by id into database
     *
     * @param mitreId1 start node
     * @param mitreId2 end node
     * @throws Exception if fails
     */
    @Query("MATCH (m), (n) WHERE m.mitre_id=$m1 AND n.mitre_id=$m2 MERGE (m)-[r:in]->(n) MERGE (n)-[re:contains]->(m);")
    void createInRelationshipByMitreId(@Param("m1") String mitreId1, @Param("m2") String mitreId2) throws Exception;
    
    /**
     * create relationship 'uses' between two objects by id into database
     *
     * @param mitreId1 start node
     * @param mitreId2 end node
     * @throws Exception if fails
     */
    @Query("MATCH (m), (n) WHERE m.mitre_id=$m1 AND n.mitre_id=$m2 MERGE (m)-[r:uses]->(n) " +
            "MERGE (n)-[re:`is used by`]->(m);")
    void createUsesRelationshipByMitreId(@Param("m1") String mitreId1, @Param("m2") String mitreId2) throws Exception;
    
    /**
     * create relationship 'is used by' between two objects by id into database
     *
     * @param mitreId1 start node
     * @param mitreId2 end node
     * @throws Exception if fails
     */
    @Query("MATCH (m), (n) WHERE m.mitre_id=$m1 AND n.mitre_id=$m2 " +
            "MERGE (m)-[r:`is used by`]->(n) MERGE (n)-[re:`uses`]->(m);")
    void createUsedByRelationshipByMitreId(@Param("m1") String mitreId1, @Param("m2") String mitreId2)
            throws Exception;
    
    /**
     * delete an object by id from database
     *
     * @param mitreId id
     * @throws Exception if fails
     */
    @Query("MATCH (m) WHERE m.mitre_id=$m1 DELETE m;")
    void deleteByMitreId(@Param("m1") String mitreId) throws Exception;
    
    /**
     * delete all relationships of an object by id from database
     *
     * @param mitreId id
     * @throws Exception if fails
     */
    @Query("MATCH (m)-[r]->(n), (n)-[re]->(m) WHERE m.mitre_id=$m1 DELETE r, re;")
    void deleteRelationships(@Param("m1") String mitreId) throws Exception;
    
    /**
     * delete relationship between two objects by id from database
     *
     * @param mitreId1 start node
     * @param mitreId2 end node
     * @throws Exception if fails
     */
    @Query("MATCH (m)-[r]->(n), (n)-[re]->(m) WHERE m.mitre_id=$m1 AND n.mitre_id=$m2 DELETE r, re;")
    void deleteRelationshipByStartNodeMitreId(@Param("m1") String mitreId1, @Param("m2") String mitreId2)
            throws Exception;
}
