package uestc.zhanghanwen.ATTCK.Repositories;

import uestc.zhanghanwen.ATTCK.POJOs.Group;
import org.springframework.stereotype.Repository;

/**
 * The DAO {@link NodeRepository} for POJO {@link Group}. <br>
 * That all the query methods are from this class.
 *
 * @see uestc.zhanghanwen.ATTCK.POJOs.GraphNode
 * @see NodeRepository
 * @see Group
 * @author zhanghanwen
 * @version 1.0
 */
@Repository
public interface GroupRepo extends NodeRepository<Group> {}
