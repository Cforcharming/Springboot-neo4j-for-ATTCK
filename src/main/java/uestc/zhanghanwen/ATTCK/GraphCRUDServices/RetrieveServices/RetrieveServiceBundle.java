package uestc.zhanghanwen.ATTCK.GraphCRUDServices.RetrieveServices;

import uestc.zhanghanwen.ATTCK.GraphCRUDServices.RetrieveServices.Implements.*;
import uestc.zhanghanwen.ATTCK.GraphCRUDServices.ServiceInterface;
import uestc.zhanghanwen.ATTCK.GraphCRUDServices.ServiceBundle;
import uestc.zhanghanwen.ATTCK.Wrappers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import java.util.Map.Entry;

/**
 * This is the consultation class for all POJO retrieve services.<br>
 * That all services are accessed by one single group of API provided by this class,
 * that is, {@link RetrieveService}.<br>
 * For specifications of the interfaces, see {@link RetrieveServiceBundle#RetrieveServiceBundle}
 *
 * @see RetrieveService
 * @author zhanghanwen
 * @version 1.0
 */
@Service
public class RetrieveServiceBundle extends ServiceBundle {

    /**
     * {@link Autowired} of spring services listed below. The service implements are listed as below.<br>
     * The services are managed and accessed by a simple array of type {@link RetrieveService}.
     *
     * @param mitigationService {@link RetrieveMitigationServiceImpl}
     * @param techniqueService {@link RetrieveTechniqueServiceImpl}
     * @param softwareService {@link RetrieveSoftwareServiceImpl}
     * @param tacticService {@link RetrieveTacticServiceImpl}
     * @param matrixService {@link RetrieveMatrixServiceImpl}
     * @param groupService {@link RetrieveGroupServiceImpl}
     */
    @Autowired
    public RetrieveServiceBundle(
            RetrieveMitigationServiceImpl mitigationService,
            RetrieveTechniqueServiceImpl techniqueService,
            RetrieveSoftwareServiceImpl softwareService,
            RetrieveTacticServiceImpl tacticService,
            RetrieveMatrixServiceImpl matrixService,
            RetrieveGroupServiceImpl groupService
    ) {
        this.getServices().put("mitigation", mitigationService);
        this.getServices().put("technique", techniqueService);
        this.getServices().put("software", softwareService);
        this.getServices().put("tactic", tacticService);
        this.getServices().put("matrix", matrixService);
        this.getServices().put("group", groupService);
    }
    
    /**
     * Get the node by its mitre id.
     *
     * @param query the {@link QueryWrapper} of wrapped query of mitre id.
     * @return {@link ResponseWrapper} wrapped response
     */
    public ResponseWrapper findByName(QueryWrapper query) {
        
        String name = query.getName();
        
        ResponseWrapper response = new ResponseWrapper();
    
        try {
            for (Entry<String, ServiceInterface> entry: this.getServices().entrySet()) {
                response.addAll(((RetrieveService) entry.getValue()).findByName(name));
            }
        } catch (Exception e) {
            response.addAll(ResultWrapper.errorResult(e));
        }
        return response;
    }
    
    /**
     * Get the node by its name.
     *
     * @param query the {@link QueryWrapper} of wrapped query of name.
     * @return {@link ResponseWrapper} wrapped response
     */
    public ResponseWrapper findByMitreId(QueryWrapper query) {
        
        String mitreId = query.getMitreId();
        String type = query.getType();
        ResponseWrapper response = new ResponseWrapper();
    
        RetrieveService service = (RetrieveService) this.getService(type);
    
        if (service != null) {
            
            try {
                response.addAll(service.findByMitreId(mitreId));
                
            } catch (Exception e) {
                response.addAll(ResultWrapper.errorResult(e));
            }
        } else {
            response.addAll(ResultWrapper.wrongTypeResult(type));
        }
        return response;
    }
    
    /**
     * Get all nodes by type, with page and size specified.<br>
     * if page and size are both -1, then return all results.
     *
     * @param query the {@link QueryWrapper} of wrapped query of type.
     * @return {@link ResponseWrapper} wrapped response
     */
    public ResponseWrapper findAll(QueryWrapper query) {
        
        String type = query.getType();
        int page = query.getPage();
        int size = query.getSize();
        ResponseWrapper response = new ResponseWrapper();
        
        RetrieveService service = (RetrieveService) this.getService(type);
    
        if (service != null) {
            try {
                response.addAll(service.findAll(type, page, size));
                
            } catch (Exception e) {
                response.addAll(ResultWrapper.errorResult(e));
            }
        } else {
            response.addAll(ResultWrapper.wrongTypeResult(type));
        }
        return response;
    }
    
    /**
     * Get all related nodes by mitre id.
     *
     * @param query the {@link QueryWrapper} of wrapped query of type.
     * @return {@link ResponseWrapper} wrapped response
     */
    public ResponseWrapper findRelatedWithoutRelationshipName(QueryWrapper query) {

        String miterId = query.getMitreId();
        
        ResponseWrapper current = findByMitreId(query);
        
        //if not found or error
        if (current.getStatus() != ResponseWrapper.OK) {
            return current;
        }
        
        ResponseWrapper response = new ResponseWrapper();
        
        JSONArray result = new JSONArray();
    
        JSONObject original = new JSONObject();
        original.put("original", current.getResult().get(0));
        result.add(0, original);
        
        JSONObject ends = new JSONObject(1);
        ends.put("related", new JSONArray());
        
        result.add(1, ends);
        response.setResult(result);
    
        ResultWrapper relatedWrapper;
        try {
            relatedWrapper = ((RetrieveService) this.getService(query.getType()))
                    .findRelatedNodes(miterId);
            
        } catch (Exception e) {
            response.addAll(ResultWrapper.errorResult(e));
            return response;
        }
        
        if (relatedWrapper.getStatusCode() == ResultWrapper.NO_RECORD) {
            response.setStatus(ResponseWrapper.NO_RECORD);
            response.setDetail(relatedWrapper.getMsgSpec());
            return response;
        }
    
        if (relatedWrapper.getStatusCode() != ResultWrapper.OK) {
            response.setStatus(ResponseWrapper.REQUEST_ERROR);
            response.setDetail(relatedWrapper.getMsgSpec());
            return response;
        }
        
        ((JSONArray) ends.get("related")).addAll(relatedWrapper.getResult());
        response.setStatus(ResponseWrapper.OK);
        response.setDetail(relatedWrapper.getMsgSpec());
    
        return response;
    }
    
    /**
     * Get the specified relationship name between two given nodes.
     *
     * @param query the {@link QueryWrapper} of wrapped query of type.
     * @return {@link ResponseWrapper} wrapped response
     */
    public ResponseWrapper findRelationship(QueryWrapper query) {
        
        ResponseWrapper response = new ResponseWrapper();
        String startNodeId = ((JSONObject) query.getNodes().get(0)).getString("mitre_id");
        String endNodeId = ((JSONObject) query.getNodes().get(1)).getString("mitre_id");
        String type = query.getType();
        
        RetrieveService service = (RetrieveService) this.getService(type);
        
        if (service != null) {
            try {
                response.addAll(service.findRelationship(startNodeId, endNodeId));
                
            } catch (Exception e) {
                response.addAll(ResultWrapper.errorResult(e));
            }
        } else {
            response.addAll(ResultWrapper.wrongTypeResult(type));
        }
        
        return response;
    }
}
