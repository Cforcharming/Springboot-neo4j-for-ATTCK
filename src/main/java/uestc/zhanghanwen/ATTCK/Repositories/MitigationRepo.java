package uestc.zhanghanwen.ATTCK.Repositories;

import uestc.zhanghanwen.ATTCK.POJOs.Mitigation;
import org.springframework.stereotype.Repository;

/**
 * The DAO {@link NodeRepository} for POJO {@link Mitigation}. <br>
 * That all the query methods are from this class.
 *
 * @see uestc.zhanghanwen.ATTCK.POJOs.GraphNode
 * @see NodeRepository
 * @see Mitigation
 * @author zhanghanwen
 * @version 1.0
 */
@Repository
public interface MitigationRepo extends NodeRepository<Mitigation> {}
