package uestc.zhanghanwen.ATTCK.GraphCRUDServices.RetrieveServices.Implements;

import uestc.zhanghanwen.ATTCK.Repositories.TechniqueRepo;
import uestc.zhanghanwen.ATTCK.POJOs.Technique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is the service implementation class for {@link TechniqueRepo}. <br>
 * The return types are all {@code List<Technique>}
 *
 * @see TechniqueRepo
 * @author zhanghanwen
 * @version 1.0
 */
@Service
public class RetrieveTechniqueServiceImpl extends RetrieveServiceImplement<Technique, TechniqueRepo> {

    @Autowired
    public RetrieveTechniqueServiceImpl(TechniqueRepo repository) {
        this.setRepo(repository);
    }
}
