package uestc.zhanghanwen.ATTCK.GraphCRUDServices.UpdateServices.Implements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uestc.zhanghanwen.ATTCK.POJOs.Tactic;
import uestc.zhanghanwen.ATTCK.Repositories.TacticRepo;

/**
 * This is the service implementation class for {@link TacticRepo}. <br>
 * The return types are all {@code List<Tactic>}
 *
 * @see TacticRepo
 * @author zhanghanwen
 * @version 1.0
 */
@Service
public class UpdateTacticServiceImpl extends UpdateServiceImplement<Tactic, TacticRepo> {

    @Autowired
    public UpdateTacticServiceImpl(TacticRepo repository) {
        this.setRepo(repository);
    }
}
