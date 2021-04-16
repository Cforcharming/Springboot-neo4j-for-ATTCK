package uestc.zhanghanwen.ATTCK.GraphCRUDServices.UpdateServices.Implements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uestc.zhanghanwen.ATTCK.POJOs.Mitigation;
import uestc.zhanghanwen.ATTCK.Repositories.MitigationRepo;

/**
 * This is the service implementation class for {@link MitigationRepo}. <br>
 * The return types are all {@code List<Mitigation>}
 *
 * @see MitigationRepo
 * @author zhanghanwen
 * @version 1.0
 */
@Service
public class UpdateMitigationServiceImpl extends UpdateServiceImplement<Mitigation, MitigationRepo> {

    @Autowired
    public UpdateMitigationServiceImpl(MitigationRepo repository) {
        this.setRepo(repository);
    }
}
