package ch;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenhang
 * @date 2018/7/13 下午10:11
 * @description
 */
@RestController
public class HelloController {


    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String index(){
        return "Hello World!!!!!";
    }
}
