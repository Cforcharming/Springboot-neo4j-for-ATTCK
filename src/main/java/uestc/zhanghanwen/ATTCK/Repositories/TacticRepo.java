package uestc.zhanghanwen.ATTCK.Repositories;

import uestc.zhanghanwen.ATTCK.POJOs.Tactic;
import org.springframework.stereotype.Repository;

/**
 * The DAO {@link NodeRepository} for POJO {@link Tactic}. <br>
 * That all the query methods are from this class.
 *
 * @see uestc.zhanghanwen.ATTCK.POJOs.GraphNode
 * @see NodeRepository
 * @see Tactic
 * @author zhanghanwen
 * @version 1.0
 */
@Repository
public interface TacticRepo extends NodeRepository<Tactic> {}
