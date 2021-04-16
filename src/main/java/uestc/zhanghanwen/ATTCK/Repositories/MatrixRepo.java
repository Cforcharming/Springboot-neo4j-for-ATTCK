package uestc.zhanghanwen.ATTCK.Repositories;

import uestc.zhanghanwen.ATTCK.POJOs.Matrix;
import org.springframework.stereotype.Repository;

/**
 * The DAO {@link NodeRepository} for POJO {@link Matrix}. <br>
 * That all the query methods are from this class.
 *
 * @see uestc.zhanghanwen.ATTCK.POJOs.GraphNode
 * @see NodeRepository
 * @see Matrix
 * @author zhanghanwen
 * @version 1.0
 */
@Repository
public interface MatrixRepo extends NodeRepository<Matrix> {}
