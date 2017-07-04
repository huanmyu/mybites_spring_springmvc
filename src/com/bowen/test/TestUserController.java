package com.bowen.test;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bowen.bean.User;
import com.bowen.service.UserService;

public class TestUserController {
	public static void main(String[] args) throws Exception{  
        
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");  
        System.out.println(context);
        UserService userService = (UserService)context.getBean("userService");  
       List<User> list = new ArrayList<User>();  
        list.add(new User(3,"wang3", "wang"));  
        list.add(new User(4,"wang4", "wang"));  
        list.add(new User(5,"wang5", "wang"));  
        userService.insertUsers(list);  
        System.out.println("恭喜恭喜，添加成功！");  
        userService.deleteUser(5);
        System.out.println("恭喜恭喜，删除成功！");  
        User beanUser = userService.getUserById(1);  
        if (beanUser != null) {  
            System.out.println("恭喜恭喜，查找成功！");  
        }else {  
            System.out.println("不好意思，您所查找的用户不存在！");  
        }  
        int totalCount = userService.getTotalCount();  
        System.out.println("TotalCount="+totalCount);  
        int result = userService.deleteUserByLike(new User(4,"wang","wang"));  
        System.out.println("模糊删除的记录条数是："+result);  
       List users = userService.getUsersByLike(new User(4,"wang4", "wang"));  
        if (users == null || users.size() == 0) {  
            System.out.println("没有匹配的记录2");  
        }else {  
            for (int i = 0; i < users.size(); i++) {  
                User user = (User)users.get(i);  
                System.out.println("username="+user.getUsername()+",password="+user.getPassword());  
            }  
        }  
        System.out.println("length="+users.size());
    }  
}
