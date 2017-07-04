package com.bowen.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bowen.bean.User;
import com.bowen.dao.UserDao;

@Component  //将UserService类注入到bean里面  
@Transactional  //注入事务管理  
public class UserService {  
      
    @Resource(name="userDao")  // 通过名称注入到bean里面  
    private UserDao userDao;  
  
    public User login(User user) throws Exception{  
        return userDao.getUserByNameAndPassword(user);  
    }  
      
    public void addUser(User user) throws Exception{  
        userDao.addUser(user);  
    }  
      
    public void deleteUser(int id) throws Exception{  
        boolean bool = userDao.deleteUser(id);  
        if (!bool) {  
            System.out.println("删除的记录不存在！");  
            throw new RuntimeException();  
        }  
    }  
      
    public User getUserById(int id) throws Exception{  
        User user = userDao.getUserById(id);  
        if (user == null) {  
            return null;  
        }  
        return userDao.getUserById(id);  
    }  
      
    public void updateUser(User user) throws Exception{  
        userDao.updateUser(user);  
    }  
      
    public int getTotalCount() throws Exception{  
        return userDao.getTotalCount();  
    }  
      
    @SuppressWarnings("unchecked")  
    public List getUsersByLike(User user) throws Exception{  
        return userDao.getUsersByLike(user);  
    }  
      
    public int deleteUserByLike(User user) throws Exception{  
        return userDao.deleteUserByLike(user);  
    }  
      
    public void insertUsers(List<User> list) throws Exception{  
        for (int i = 0; i < list.size(); i++) {  
            if (i > 3) {  
                System.out.println("列表太长，中断事务");  
                throw new RuntimeException("中断事务异常,当列表长度大于3的时候故意抛出，看看事务是否回滚");  
            }  
            User user = list.get(i);  
            userDao.addUser(user);  
        }  
    }  
}  
