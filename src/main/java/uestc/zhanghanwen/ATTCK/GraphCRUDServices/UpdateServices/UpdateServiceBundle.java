package uestc.zhanghanwen.ATTCK.GraphCRUDServices.UpdateServices;

import uestc.zhanghanwen.ATTCK.GraphCRUDServices.UpdateServices.Implements.*;
import uestc.zhanghanwen.ATTCK.GraphCRUDServices.ServiceBundle;
import uestc.zhanghanwen.ATTCK.Wrappers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;

/**
 * This is the consultation class for all POJO services.<br>
 * That all services are accessed by one single group of API provided by this class,
 * that is, {@link UpdateService}.<br>
 *
 * @see UpdateService
 * @author zhanghanwen
 * @version 1.0
 */@Service
public class UpdateServiceBundle extends ServiceBundle {

    /**
     * Autowire({@link Autowired}) of spring services listed below. The service implements are listed as below.<br>
     * The services are managed and accessed by a simple array of type {@link UpdateService}.
     *
     * @param mitigationService {@link UpdateMitigationServiceImpl}
     * @param techniqueService {@link UpdateTechniqueServiceImpl}
     * @param softwareService {@link UpdateSoftwareServiceImpl}
     * @param tacticService {@link UpdateTacticServiceImpl}
     * @param matrixService {@link UpdateMatrixServiceImpl}
     * @param groupService {@link UpdateGroupServiceImpl}
     */
    @Autowired
    public UpdateServiceBundle(
            UpdateMitigationServiceImpl mitigationService,
            UpdateTechniqueServiceImpl techniqueService,
            UpdateSoftwareServiceImpl softwareService,
            UpdateTacticServiceImpl tacticService,
            UpdateMatrixServiceImpl matrixService,
            UpdateGroupServiceImpl groupService
    ) {
        this.getServices().put("mitigation", mitigationService);
        this.getServices().put("technique", techniqueService);
        this.getServices().put("software", softwareService);
        this.getServices().put("tactic", tacticService);
        this.getServices().put("matrix", matrixService);
        this.getServices().put("group", groupService);
    }
    
    public ResponseWrapper mergeNode(QueryWrapper query) {

        JSONObject node = query.getNodes().getJSONObject(0);
        String mitreId = node.getString("mitre_id");
        String type = node.getString("type");
        
        ResponseWrapper response = new ResponseWrapper();
    
        if (!this.isNodeExist(mitreId)) {
            response.setStatus(ResponseWrapper.NO_RECORD);
            response.setMsg(ResponseWrapper.NO_RECORD_MSG);
            response.setDetail("the start node of mitre id '" + mitreId + "' is not found.");
            return response;
        }
    
        try {
            response.addAll(((UpdateService) services.get(type)).mergeOneNode(node));
        } catch (Exception e) {
            response.addAll(ResultWrapper.errorResult(e));
        }
        return response;
    }
}
