package uestc.zhanghanwen.ATTCK.GraphCRUDServices.CreateServices.Implements;

import uestc.zhanghanwen.ATTCK.Repositories.GroupRepo;
import uestc.zhanghanwen.ATTCK.POJOs.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is the service implementation class for {@link GroupRepo}. <br>
 * The return types are all {@code List<Group>}
 *
 * @see GroupRepo
 * @author zhanghanwen
 * @version 1.0
 */
@Service
public class CreateGroupServiceImpl extends CreateServiceImplement<Group, GroupRepo> {

    @Autowired
    public CreateGroupServiceImpl(GroupRepo repository) {
        this.setRepo(repository);
    }
}
