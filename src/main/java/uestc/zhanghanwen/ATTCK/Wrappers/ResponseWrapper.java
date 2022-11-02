package uestc.zhanghanwen.ATTCK.Wrappers;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSON;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import lombok.Data;

/**
 * The HTTP response should be sent to the client.<br>
 * The object can be serialized into {@code JSON string} via {@link ResponseWrapper#toString}
 *
 * @author zhanghanwen
 * @version 1.0
 */
@Data
public class ResponseWrapper {

    /**
     * OK response. value = {@value}
     */
    @JSONField(serialize = false, deserialize = false)
    public static final int OK = 0;
    
    /**
     * Error request response. value = {@value}
     */
    @JSONField(serialize = false, deserialize = false)
    public static final int REQUEST_ERROR = 1;
    
    /**
     * Server internal error response. value = {@value}
     */
    @JSONField(serialize = false, deserialize = false)
    public static final int INTERNAL_ERROR = 2;
    
    /**
     * Code for no records. value={@value}
     */
    @JSONField(serialize = false, deserialize = false)
    public static final int NO_RECORD = 3;
    
    /**
     * The record already exists if the query is to create. value = {@value}
     */
    @JSONField(serialize = false, deserialize = false)
    public static final int ALREADY_EXIST = 4;
    
    /**
     * The message of {@link ResponseWrapper#OK}, value = {@value}
     */
    @JSONField(serialize = false, deserialize = false)
    public static final String OK_MSG = "OK";
    
    /**
     * The message of {@link ResponseWrapper#REQUEST_ERROR}, value = {@value}
     */
    @JSONField(serialize = false, deserialize = false)
    public static final String REQUEST_ERROR_MSG = "The parameters are contradicted from required.";
    
    /**
     * The message of {@link ResponseWrapper#INTERNAL_ERROR}, value = {@value}
     */
    @JSONField(serialize = false, deserialize = false)
    public static final String INTERNAL_ERROR_MSG = "Server internal error.";
    
    /**
     * The message of {@link ResponseWrapper#NO_RECORD}, value = {@value}
     */
    @JSONField(serialize = false, deserialize = false)
    public static final String NO_RECORD_MSG = "No record found in graph database.";
    
    /**
     * The message of {@link ResponseWrapper#ALREADY_EXIST}, value = {@value}
     */
    @JSONField(serialize = false, deserialize = false)
    public static final String ALREADY_EXIST_MSG = "Could not insert, " +
            "because it already exists in graph database, try update instead.";
    
    /**
     * The status of the current response.
     *
     * @see ResponseWrapper#OK
     * @see ResponseWrapper#REQUEST_ERROR
     * @see ResponseWrapper#INTERNAL_ERROR
     * @see ResponseWrapper#NO_RECORD
     * @see ResponseWrapper#ALREADY_EXIST
     */
    @JSONField(name = "status", ordinal = 1)
    private int status;
    
    /**
     * The specified results of the query.
     */
    @NotNull
    @JSONField(name = "result", ordinal = 2)
    private JSONArray result;
    
    /**
     * The message of the current response.
     *
     * @see ResponseWrapper#OK_MSG
     * @see ResponseWrapper#REQUEST_ERROR_MSG
     * @see ResponseWrapper#INTERNAL_ERROR_MSG
     * @see ResponseWrapper#NO_RECORD_MSG
     * @see ResponseWrapper#ALREADY_EXIST_MSG
     */
    @NotNull
    @JSONField(name = "msg", ordinal = 3)
    private String msg;
    
    /**
     * The message for detail provided by {@link ResultWrapper}
     *
     * @see ResultWrapper#getMsgSpec
     */
    @JSONField(name = "detail", ordinal = 4)
    private String detail;
    
    /**
     * Default constructor.
     */
    @Contract(pure = true)
    public ResponseWrapper() {
        this.setResult(new JSONArray());
        this.setStatus(ResponseWrapper.NO_RECORD);
    }

    /**
     * Add all results from a {@link JSONArray} in a {@link ResultWrapper} into its own {@link ResponseWrapper#result}
     *
     * @param result the {@link ResultWrapper} of a query.
     */
    public void addAll(ResultWrapper result) {
    
        switch (result.getStatusCode()) {
            
            case ResultWrapper.OK:
            
                if (this.status == ResponseWrapper.OK || this.status == NO_RECORD) {
                    this.getResult().addAll(result.getResult());
                    this.setStatus(ResponseWrapper.OK, result.getMsgSpec());
                }
                break;
                
            case ResultWrapper.NO_RECORD:
            
                if (this.status == ResponseWrapper.NO_RECORD) {
                    this.setDetail(result.getMsgSpec());
                }
                break;
                
            case ResultWrapper.FAILED:
            
                this.setStatus(ResponseWrapper.INTERNAL_ERROR, result.getMsgSpec());
                break;
                
            case ResultWrapper.ALREADY_EXIST:
            
                if (this.status == ResponseWrapper.NO_RECORD) {
                    this.setStatus(ResponseWrapper.ALREADY_EXIST, result.getMsgSpec());
                }
                break;
                
            case ResultWrapper.REQUEST_ERROR:
            
                this.setStatus(ResponseWrapper.REQUEST_ERROR, result.getMsgSpec());
                break;
        }
        
    }

    /**
     * Factory mode for generating request parameter error response.
     *
     * @param detail detail of the error.
     * @return error response.
     */
    public static ResponseWrapper paramErrorResponseFactory(String detail) {
        ResponseWrapper paramErrorResponse = new ResponseWrapper();
        paramErrorResponse.setStatus(ResponseWrapper.REQUEST_ERROR, detail);
        return paramErrorResponse;
    }
    
    /**
     * Setter for {@link ResponseWrapper#status}.<br>
     * Also sets the corresponding {@link ResponseWrapper#msg}
     *
     * @param status the status to be set, note that it won't check for correctness.
     * @see ResponseWrapper#OK
     * @see ResponseWrapper#REQUEST_ERROR
     * @see ResponseWrapper#INTERNAL_ERROR
     * @see ResponseWrapper#NO_RECORD
     * @see ResponseWrapper#ALREADY_EXIST
     */
    public void setStatus(int status) {
        
        this.status = status;
        
        if (status == ResponseWrapper.OK) {
            this.setMsg(ResponseWrapper.OK_MSG);
            
        } else if (status == ResponseWrapper.NO_RECORD) {
            this.setMsg(ResponseWrapper.NO_RECORD_MSG);
            
        } else if (status == ResponseWrapper.REQUEST_ERROR) {
            this.setMsg(ResponseWrapper.REQUEST_ERROR_MSG);
            
        } else if (status == ResponseWrapper.ALREADY_EXIST) {
            this.setMsg(ResponseWrapper.ALREADY_EXIST_MSG);
            
        } else if (status == ResponseWrapper.INTERNAL_ERROR) {
            this.setMsg(ResponseWrapper.INTERNAL_ERROR_MSG);
        }
    }
    
    /**
     * Setter for {@link ResponseWrapper#status}.<br>
     * Also sets the corresponding {@link ResponseWrapper#msg} and {@link ResponseWrapper#detail}
     *
     * @param status the status to be set, note that it won't check for correctness.
     * @param detail the detail to be set.
     * @see ResponseWrapper#OK
     * @see ResponseWrapper#REQUEST_ERROR
     * @see ResponseWrapper#INTERNAL_ERROR
     * @see ResponseWrapper#NO_RECORD
     * @see ResponseWrapper#ALREADY_EXIST
     */
    public void setStatus(int status, String detail) {
        this.setStatus(status);
        this.setDetail(detail);
    }
    
    
    /**
     * Serialize the object into {@link String} in the format of {@code JSON}
     *
     * @return {@code JSON string}
     */
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
