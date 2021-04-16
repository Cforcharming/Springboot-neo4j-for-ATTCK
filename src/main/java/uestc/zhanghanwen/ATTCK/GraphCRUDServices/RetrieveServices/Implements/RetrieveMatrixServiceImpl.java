package uestc.zhanghanwen.ATTCK.GraphCRUDServices.RetrieveServices.Implements;

import uestc.zhanghanwen.ATTCK.Repositories.MatrixRepo;
import uestc.zhanghanwen.ATTCK.POJOs.Matrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is the service implementation class for {@link MatrixRepo}. <br>
 * The return types are all {@code List<Matrix>}
 *
 * @see MatrixRepo
 * @author zhanghanwen
 * @version 1.0
 */
@Service
public class RetrieveMatrixServiceImpl extends RetrieveServiceImplement<Matrix, MatrixRepo> {

    @Autowired
    public RetrieveMatrixServiceImpl(MatrixRepo repository) {
        this.setRepo(repository);
    }
}
