package uestc.zhanghanwen.ATTCK.GraphCRUDServices.DeleteServices.Implements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uestc.zhanghanwen.ATTCK.POJOs.Software;
import uestc.zhanghanwen.ATTCK.Repositories.SoftwareRepo;

/**
 * This is the service implementation class for {@link SoftwareRepo}. <br>
 * The return types are all {@code List<Software>}
 *
 * @see SoftwareRepo
 * @author zhanghanwen
 * @version 1.0
 */
@Service
public class DeleteSoftwareServiceImpl extends DeleteServiceImplement<Software, SoftwareRepo> {

    @Autowired
    public DeleteSoftwareServiceImpl(SoftwareRepo repository) {
        this.setRepo(repository);
    }
}
