package uestc.zhanghanwen.ATTCK.GraphCRUDServices.UpdateServices.Implements;

import uestc.zhanghanwen.ATTCK.GraphCRUDServices.UpdateServices.UpdateService;
import uestc.zhanghanwen.ATTCK.GraphCRUDServices.ATTCKService;
import uestc.zhanghanwen.ATTCK.Repositories.NodeRepository;
import uestc.zhanghanwen.ATTCK.Wrappers.ResultWrapper;
import uestc.zhanghanwen.ATTCK.POJOs.GraphNode;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;

/**
 * This class has the implementation of methods in {@link UpdateService} used in <br>
 * {@link uestc.zhanghanwen.ATTCK.GraphCRUDServices.UpdateServices.UpdateServiceBundle}
 *
 * @author zhanghanwen
 * @version 1.0
 */
abstract class UpdateServiceImplement<GN extends GraphNode, NR extends NodeRepository<GN>>
        extends ATTCKService<GN, NR>
        implements UpdateService {

    /**
     * update one node.
     *
     * @param node the node to be saved.
     * @return {@link JSONArray} of all queried results.
     * @throws Exception if {@link NodeRepository#save} fails or the mitre id of the saved node is not matched
     * as the expected input, then rollback.
     * @see ATTCKService#save
     */
    @Override
    @Transactional
    public ResultWrapper mergeOneNode(JSONObject node) throws Exception {
        return this.save(node);
    }
}
