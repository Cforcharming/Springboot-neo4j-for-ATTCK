package uestc.zhanghanwen.ATTCK.GraphCRUDServices.CreateServices;

import uestc.zhanghanwen.ATTCK.GraphCRUDServices.CreateServices.Implements.*;
import uestc.zhanghanwen.ATTCK.GraphCRUDServices.ServiceBundle;
import uestc.zhanghanwen.ATTCK.POJOs.GraphNode;
import uestc.zhanghanwen.ATTCK.Wrappers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;

/**
 * This is the consultation class for all POJO services.<br>
 * That all services are accessed by one single group of API provided by this class, which is<br>
 * {@link CreateService}.
 *
 * @see CreateService
 * @author zhanghanwen
 * @version 1.0
 */
@Service
public class CreateServiceBundle extends ServiceBundle {

    /**
     * Autowire({@link Autowired}) of spring services listed below. The service implements are listed as below.<br>
     * The services are managed and accessed by a simple array of type {@link CreateService}.
     *
     * @param mitigationService {@link CreateMitigationServiceImpl}
     * @param techniqueService {@link CreateTechniqueServiceImpl}
     * @param softwareService {@link CreateSoftwareServiceImpl}
     * @param tacticService {@link CreateTacticServiceImpl}
     * @param matrixService {@link CreateMatrixServiceImpl}
     * @param groupService {@link CreateGroupServiceImpl}
     */
    @Autowired
    public CreateServiceBundle(
            CreateMitigationServiceImpl mitigationService,
            CreateTechniqueServiceImpl techniqueService,
            CreateSoftwareServiceImpl softwareService,
            CreateTacticServiceImpl tacticService,
            CreateMatrixServiceImpl matrixService,
            CreateGroupServiceImpl groupService
    ) {
        this.getServices().put("mitigation", mitigationService);
        this.getServices().put("technique", techniqueService);
        this.getServices().put("software", softwareService);
        this.getServices().put("tactic", tacticService);
        this.getServices().put("matrix", matrixService);
        this.getServices().put("group", groupService);
    }
    
    /**
     * create a node.
     *
     * @param query {@link QueryWrapper} containing the node.
     * @return {@link ResponseWrapper} of response.
     */
    public ResponseWrapper createNode(QueryWrapper query) {
        
        JSONObject node = query.getNodes().getJSONObject(0);
        String type = query.getType();
        ResponseWrapper response = new ResponseWrapper();
        
        CreateService service = (CreateService) this.getService(type);
        
        if (service == null) {
            response.addAll(ResultWrapper.wrongTypeResult(type));
            return response;
        }
        
        if (this.isNodeExist(query.getMitreId())) {
            response.setStatus(ResponseWrapper.ALREADY_EXIST);
            response.setMsg(ResponseWrapper.ALREADY_EXIST_MSG);
            return response;
        }
        
        try {
            response.addAll(service.createNode(node));
            
        } catch (Exception e) {
            response.addAll(ResultWrapper.errorResult(e));
        }
        return response;
    }
    
    /**
     * create a pair of relationships by specifying start node and end node mitre id.
     *
     * @param query {@link QueryWrapper} start node and end node mitre id
     * @return {@link ResponseWrapper} of response.
     */
    public ResponseWrapper createRelationship(QueryWrapper query) {

        String startNodeMitreId = query.getNodes().getJSONObject(0).getString("mitre_id");
        String endNodeMitreId = query.getNodes().getJSONObject(1).getString("mitre_id");
        String type = GraphNode.getTypeFromMitreId(startNodeMitreId);
        String relationship = query.getRelationship();
        
        ResponseWrapper response = new ResponseWrapper();
        
        CreateService service = (CreateService) this.getService(type);
        
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
        
        if (this.isRelationshipExist(startNodeMitreId, endNodeMitreId)) {
            response.setStatus(ResponseWrapper.ALREADY_EXIST);
            response.setMsg(ResponseWrapper.ALREADY_EXIST_MSG);
            response.setDetail("The relationship between object '"
                    + startNodeMitreId + "' and '" + endNodeMitreId + "' object already exists");
            return response;
        }
        
        try {
            response.addAll(service.createRelationship(type, startNodeMitreId, endNodeMitreId, relationship));
            
        } catch (Exception e) {
            response.addAll(ResultWrapper.errorResult(e));
        }
        return response;
    }
}
