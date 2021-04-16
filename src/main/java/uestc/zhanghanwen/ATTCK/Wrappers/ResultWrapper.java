package uestc.zhanghanwen.ATTCK.Wrappers;

import uestc.zhanghanwen.ATTCK.POJOs.GraphNode;
import com.alibaba.fastjson.JSONArray;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import java.util.Objects;
import java.util.List;
import lombok.Data;

/**
 * The encapsulation of query results to the database.
 *
 * @author zhanghanwen
 * @version 1.0
 */
@Data
public class ResultWrapper {

    /**
     * Successfully performed a query. value = {@value}
     */
    public static final int OK = 0;

    /**
     * No record found in the database. value = {@value}
     */
    public static final int NO_RECORD = 1;

    /**
     * Failed to query. value = {@value}
     */
    public static final int FAILED = 2;
    
    /**
     * Already exists if the query is to create. value = {@value}
     */
    public static final int ALREADY_EXIST = 3;
    
    /**
     * Error request response. value = {@value}
     */
    public static final int REQUEST_ERROR = 4;
    
    /**
     * The results of the query.
     */
    @NotNull
    private JSONArray result;

    /**
     * The status of the current query.
     *
     * @see ResultWrapper#OK
     * @see ResultWrapper#NO_RECORD
     * @see ResultWrapper#FAILED
     * @see ResultWrapper#ALREADY_EXIST
     * @see ResultWrapper#REQUEST_ERROR
     */
    private int statusCode;

    /**
     * The specific message of error, null if OK.
     */
    private String msgSpec;
    
    /**
     * Constructor for initializing {@link ResultWrapper#statusCode}
     * @param statusCode status code.
     */
    @Contract(pure = true)
    public ResultWrapper(int statusCode) {
        this.setStatusCode(statusCode);
    }
    
    /**
     * Default constructor.
     */
    @Contract(pure = true)
    public ResultWrapper() {
        this.setStatusCode(ResultWrapper.NO_RECORD);
    }

    public String getMsgSpec() {
        return Objects.requireNonNullElse(msgSpec, "");
    }

    /**
     * Get the results from a {@link List} given by the {@link uestc.zhanghanwen.ATTCK.Repositories.NodeRepository}
     * @param nodeList the results
     * @param <GN> instance of {@link GraphNode}
     * @return {@link ResultWrapper} of the results.
     */
    public static <GN extends GraphNode> ResultWrapper resultFromList(@NotNull List<GN> nodeList) {
        JSONArray array = new JSONArray();
        array.addAll(nodeList);
        ResultWrapper result = new ResultWrapper();
        result.setResult(array);
        if (nodeList.size() > 0) {
            result.setStatusCode(ResultWrapper.OK);
        } else {
            result.setStatusCode(ResultWrapper.NO_RECORD);
        }
        return result;
    }

    /**
     * Factory mode for query failure caused by internal error.
     *
     * @param e {@link Exception}
     * @return error result
     */
    public static ResultWrapper errorResult(Exception e) {
        ResultWrapper result = new ResultWrapper(ResultWrapper.FAILED);
        result.setMsgSpec(e.getClass() + ": " + e.getMessage());
        return result;
    }

    /**
     * Factory mode for query failure caused by wrong type provided by user.
     * @param type wrong type
     * @return error result
     */
    public static ResultWrapper wrongTypeResult(String type) {
        
        ResultWrapper result = new ResultWrapper(ResultWrapper.NO_RECORD);
        
        if (type == null || type.equals("")) {
            result.setMsgSpec("Type not provided, or cannot infer type from mitre id.");
        } else {
            result.setMsgSpec("Type '" + type + "' of node does not exist.");
        }
        return result;
    }
}
