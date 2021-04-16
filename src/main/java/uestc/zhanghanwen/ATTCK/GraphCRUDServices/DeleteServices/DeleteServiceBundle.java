package uestc.zhanghanwen.ATTCK.GraphCRUDServices.DeleteServices;

import uestc.zhanghanwen.ATTCK.GraphCRUDServices.DeleteServices.Implements.*;
import uestc.zhanghanwen.ATTCK.GraphCRUDServices.ServiceBundle;
import uestc.zhanghanwen.ATTCK.POJOs.GraphNode;
import uestc.zhanghanwen.ATTCK.Wrappers.ResponseWrapper;
import uestc.zhanghanwen.ATTCK.Wrappers.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uestc.zhanghanwen.ATTCK.Wrappers.ResultWrapper;

/**
 * This is the consultation class for all POJO services.<br>
 * That all services are accessed by one single group of API provided by this class, that is,
 * {@link DeleteService}.<br>
 *
 * @see DeleteService
 * @author zhanghanwen
 * @version 1.0
 */
@Service
public class DeleteServiceBundle extends ServiceBundle {

    /**
     * Autowire({@link Autowired}) of spring services listed below. The service implements are listed as below.<br>
     * The services are managed and accessed by a simple array of type {@link DeleteService}.
     *
     * @param mitigationService {@link DeleteMitigationServiceImpl}
     * @param techniqueService {@link DeleteTechniqueServiceImpl}
     * @param softwareService {@link DeleteSoftwareServiceImpl}
     * @param tacticService {@link DeleteTacticServiceImpl}
     * @param matrixService {@link DeleteMatrixServiceImpl}
     * @param groupService {@link DeleteGroupServiceImpl}
     */
    @Autowired
    public DeleteServiceBundle(
            DeleteMitigationServiceImpl mitigationService,
            DeleteTechniqueServiceImpl techniqueService,
            DeleteSoftwareServiceImpl softwareService,
            DeleteTacticServiceImpl tacticService,
            DeleteMatrixServiceImpl matrixService,
            DeleteGroupServiceImpl groupService
    ) {
        this.getServices().put("mitigation", mitigationService);
        this.getServices().put("technique", techniqueService);
        this.getServices().put("software", softwareService);
        this.getServices().put("tactic", tacticService);
        this.getServices().put("matrix", matrixService);
        this.getServices().put("group", groupService);
    }
    
    /**
     * delete a node.
     *
     * @param query {@link QueryWrapper} containing the node.
     * @return {@link ResponseWrapper} of response.
     */
    public ResponseWrapper deleteNode(QueryWrapper query) {
        
        String mitreId = query.getMitreId();
        String type = GraphNode.getTypeFromMitreId(mitreId);
        ResponseWrapper response = new ResponseWrapper();
        
        DeleteService service = (DeleteService) this.getService(type);
        
        if (service == null) {
            response.addAll(ResultWrapper.wrongTypeResult(type));
            return response;
        }
        
        if (!this.isNodeExist(mitreId)) {
            response.setStatus(ResponseWrapper.NO_RECORD);
            response.setMsg(ResponseWrapper.NO_RECORD_MSG);
            response.setDetail("cannot delete because the start node of mitre id '" + mitreId + "' is not found.");
            return response;
        }
        
        try {
            ResultWrapper result = service.deleteOne(mitreId);
            response.addAll(result);
            
        } catch (Exception e) {
            response.addAll(ResultWrapper.errorResult(e));
        }
        return response;
    }
    
    /**
     * delete a pair of relationships by specifying start node and end node mitre id.
     *
     * @param query {@link QueryWrapper} start node and end node mitre id
     * @return {@link ResponseWrapper} of response.
     */
    public ResponseWrapper deleteRelationship(QueryWrapper query) {

        String startNodeMitreId = query.getNodes().getJSONObject(0).getString("mitre_id");
        String endNodeMitreId = query.getNodes().getJSONObject(1).getString("mitre_id");
        String type = query.getType();
        ResponseWrapper response = new ResponseWrapper();
        
        DeleteService service = (DeleteService) this.getService(type);
    
        if (service == null) {
            response.addAll(ResultWrapper.wrongTypeResult(type));
            return response;
        }
    
        if (!this.isStartAndEndNodeExists(startNodeMitreId, endNodeMitreId)) {
            response.setStatus(ResponseWrapper.NO_RECORD);
            response.setMsg(ResponseWrapper.NO_RECORD_MSG);
            response.setDetail("the start node or the end node is not found.");
            return response;
        }
    
        if (!this.isRelationshipExist(startNodeMitreId, endNodeMitreId)) {
            response.setStatus(ResponseWrapper.NO_RECORD);
            response.setMsg(ResponseWrapper.NO_RECORD_MSG);
            response.setDetail("The relationship between object '"
                    + startNodeMitreId + "' and '" + endNodeMitreId + "' object already exists");
            return response;
        }
    
        try {
            response.addAll(service.deleteRelationship(startNodeMitreId, endNodeMitreId));
            
        } catch (Exception e) {
            response.addAll(ResultWrapper.errorResult(e));
        }
        return response;
    }
}
