package uestc.zhanghanwen.ATTCK.GraphCRUDServices.RetrieveServices.Implements;

import uestc.zhanghanwen.ATTCK.Repositories.TacticRepo;
import uestc.zhanghanwen.ATTCK.POJOs.Tactic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is the service implementation class for {@link TacticRepo}. <br>
 * The return types are all {@code List<Tactic>}
 *
 * @see TacticRepo
 * @author zhanghanwen
 * @version 1.0
 */
@Service
public class RetrieveTacticServiceImpl extends RetrieveServiceImplement<Tactic, TacticRepo> {

    @Autowired
    public RetrieveTacticServiceImpl(TacticRepo repository) {
        this.setRepo(repository);
    }
}
