import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.workbench.domain.Activity;
import com.bjpowernode.crm.workbench.service.ActivityService;
import com.bjpowernode.crm.workbench.service.impl.ActivityServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class myTest {

    @Autowired
    UserService userService;

    @Autowired
    ActivityService activityService;
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

  /*  @Test
    public void test1(){
        String[] a = {"15b9b97ae0fd442f8ae46ca106d0369f","195054275ca446689eef68c134543c2c"};
        ActivityServiceImpl activityService = new ActivityServiceImpl();
        List<Activity> activityList = activityService.queryActivityById(a);
        for (int i = 0; i < activityList.size();i++){
            Activity activity = activityList.get(i);
            System.out.println(activity.getId());
        }
    }
*/
}
