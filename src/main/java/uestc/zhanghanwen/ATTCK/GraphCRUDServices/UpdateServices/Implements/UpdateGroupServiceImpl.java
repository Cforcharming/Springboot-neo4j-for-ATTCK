package uestc.zhanghanwen.ATTCK.GraphCRUDServices.UpdateServices.Implements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uestc.zhanghanwen.ATTCK.POJOs.Group;
import uestc.zhanghanwen.ATTCK.Repositories.GroupRepo;

/**
 * This is the service implementation class for {@link GroupRepo}. <br>
 * The return types are all {@code List<Group>}
 *
 * @see GroupRepo
 * @author zhanghanwen
 * @version 1.0
 */
@Service
public class UpdateGroupServiceImpl extends UpdateServiceImplement<Group, GroupRepo> {

    @Autowired
    public UpdateGroupServiceImpl(GroupRepo repository) {
        this.setRepo(repository);
    }
}
