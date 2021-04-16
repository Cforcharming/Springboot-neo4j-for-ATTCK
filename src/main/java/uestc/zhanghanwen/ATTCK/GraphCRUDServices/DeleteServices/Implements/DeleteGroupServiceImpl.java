package uestc.zhanghanwen.ATTCK.GraphCRUDServices.DeleteServices.Implements;

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
public class DeleteGroupServiceImpl extends DeleteServiceImplement<Group, GroupRepo> {

    @Autowired
    public DeleteGroupServiceImpl(GroupRepo repository) {
        this.setRepo(repository);
    }
}
