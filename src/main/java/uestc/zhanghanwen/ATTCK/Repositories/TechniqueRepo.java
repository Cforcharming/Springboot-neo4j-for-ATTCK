package uestc.zhanghanwen.ATTCK.Repositories;

import uestc.zhanghanwen.ATTCK.POJOs.Technique;
import org.springframework.stereotype.Repository;

/**
 * The DAO {@link NodeRepository} for POJO {@link Technique}. <br>
 * That all the query methods are from this class.
 *
 * @see uestc.zhanghanwen.ATTCK.POJOs.GraphNode
 * @see NodeRepository
 * @see Technique
 * @author zhanghanwen
 * @version 1.0
 */
@Repository
public interface TechniqueRepo extends NodeRepository<Technique>  {}
