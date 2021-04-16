package uestc.zhanghanwen.ATTCK.GraphCRUDServices.CreateServices.Implements;

import uestc.zhanghanwen.ATTCK.Repositories.MitigationRepo;
import uestc.zhanghanwen.ATTCK.POJOs.Mitigation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is the service implementation class for {@link MitigationRepo}. <br>
 * The return types are all {@code List<Mitigation>}
 *
 * @see MitigationRepo
 * @author zhanghanwen
 * @version 1.0
 */
@Service
public class CreateMitigationServiceImpl extends CreateServiceImplement<Mitigation, MitigationRepo> {

    @Autowired
    public CreateMitigationServiceImpl(MitigationRepo repository) {
        this.setRepo(repository);
    }
}
