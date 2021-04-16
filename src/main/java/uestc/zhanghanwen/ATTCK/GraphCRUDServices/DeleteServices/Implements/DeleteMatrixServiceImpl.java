package uestc.zhanghanwen.ATTCK.GraphCRUDServices.DeleteServices.Implements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uestc.zhanghanwen.ATTCK.POJOs.Matrix;
import uestc.zhanghanwen.ATTCK.Repositories.MatrixRepo;

/**
 * This is the service implementation class for {@link MatrixRepo}. <br>
 * The return types are all {@code List<Matrix>}
 *
 * @see MatrixRepo
 * @author zhanghanwen
 * @version 1.0
 */
@Service
public class DeleteMatrixServiceImpl extends DeleteServiceImplement<Matrix, MatrixRepo> {

    @Autowired
    public DeleteMatrixServiceImpl(MatrixRepo repository) {
        this.setRepo(repository);
    }
}
