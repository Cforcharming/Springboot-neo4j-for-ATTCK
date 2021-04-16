package uestc.zhanghanwen.ATTCK.GraphCRUDServices.CreateServices.Implements;

import uestc.zhanghanwen.ATTCK.Repositories.SoftwareRepo;
import uestc.zhanghanwen.ATTCK.POJOs.Software;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is the service implementation class for {@link SoftwareRepo}. <br>
 * The return types are all {@code List<Software>}
 *
 * @see SoftwareRepo
 * @author zhanghanwen
 * @version 1.0
 */
@Service
public class CreateSoftwareServiceImpl extends CreateServiceImplement<Software, SoftwareRepo> {

    @Autowired
    public CreateSoftwareServiceImpl(SoftwareRepo repository) {
        this.setRepo(repository);
    }
}
