package uestc.zhanghanwen.ATTCK.GraphCRUDServices.UpdateServices.Implements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uestc.zhanghanwen.ATTCK.POJOs.Technique;
import uestc.zhanghanwen.ATTCK.Repositories.TechniqueRepo;

/**
 * This is the service implementation class for {@link TechniqueRepo}. <br>
 * The return types are all {@code List<Technique>}
 *
 * @see TechniqueRepo
 * @author zhanghanwen
 * @version 1.0
 */
@Service
public class UpdateTechniqueServiceImpl extends UpdateServiceImplement<Technique, TechniqueRepo> {

    @Autowired
    public UpdateTechniqueServiceImpl(TechniqueRepo repository) {
        this.setRepo(repository);
    }
}
