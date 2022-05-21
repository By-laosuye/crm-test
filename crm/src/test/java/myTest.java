import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class myTest {

    @Autowired
    UserService userService;

    /*@Test
    public void test(){
        User user = new User();
        user.setLoginAct("su");
        user.setLoginPwd("1123");
        Map<String,Object> map = new HashMap();
        map.put("loginAct",user.getLoginAct());
        map.put("loginPwd",user.getLoginPwd());
        User user1 = userService.queryUserByLoginActAndPwd(map);
        System.out.println(user1);
    }*/
}
