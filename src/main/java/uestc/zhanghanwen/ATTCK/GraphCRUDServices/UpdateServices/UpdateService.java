package uestc.zhanghanwen.ATTCK.GraphCRUDServices.UpdateServices;

import uestc.zhanghanwen.ATTCK.Wrappers.ResultWrapper;
import com.alibaba.fastjson.JSONObject;

/**
 * The interface for all Services inside.
 * This is the interface oriented mode, that makes easier for {@link UpdateServiceBundle} to manage all services.<br>
 *
 * @see UpdateServiceBundle
 * @author zhanghanwen
 * @version 1.0
 */
public interface UpdateService {

    /**
     * update one node.
     *
     * @throws Exception if {@link uestc.zhanghanwen.ATTCK.Repositories.NodeRepository#save} fails
     * @param node in format of {@link JSONObject}
     * @return {@link ResultWrapper} of all queried results.
     */
    ResultWrapper mergeOneNode(JSONObject node) throws Exception;
}
