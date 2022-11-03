package uestc.zhanghanwen.ATTCK.RestWebControllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import lombok.Data;

/**
 *
 */
@Data
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/login", method = RequestMethod.POST,
        produces="application/json;charset=UTF-8")
public class LoginController {

}
