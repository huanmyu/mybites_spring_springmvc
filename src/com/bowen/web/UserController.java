package com.bowen.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.bowen.bean.User;
import com.bowen.service.UserService;
@RequestMapping("/user/*")  
public class UserController {  
   
 @Resource(name="userService")  
 private UserService userService;  
 private final String LIST="redirect:/detail";  
   
  /**  
     * 登录  
     * @param user  
     * @param session  
     * @return  
     */  
    @RequestMapping("/login")  
    public ModelAndView login(@Valid User user,BindingResult result,HttpSession session) throws Exception{  
      if(!result.hasErrors()){  
        User loginUser=userService.login(user);  
        if(loginUser!=null){  
            session.setAttribute("USER", loginUser);  
            return new ModelAndView(LIST);  
        }else{  
            return new ModelAndView("redirect:/");  
        }  
      }else{  
          ModelAndView view=new ModelAndView();  
          view.setViewName("redirect:/");  
          view.addObject("error", result.getAllErrors());  
          return view;  
      }  
    }  
      
    /** 
     * 跳转至添加页 
     * @return 
     */  
    @RequestMapping(value="/toAdd",method=RequestMethod.GET)  
    public ModelAndView toAdd(){  
        return new ModelAndView("user/add");  
    }  
    /** 
     * 保存 
     * @param user 
     * @return 
     */  
    @RequestMapping(value="/add",method=RequestMethod.POST)  
    public ModelAndView add(@Valid User user,BindingResult result) throws Exception{  
        if(result.hasErrors()){  
            return new ModelAndView("user/add","error", result.getAllErrors());  
        }else{  
            userService.addUser(user);  
            return new ModelAndView(LIST);      
        }  
    }  
    /** 
     * 根据ID删除 
     * @param id 
     * @return 
     */  
    @RequestMapping(value="/delete/{id}")  
    public ModelAndView delete(@PathVariable int id) throws Exception{  
        userService.deleteUser(id);  
        return new ModelAndView(LIST);  
    }  
    /** 
     * 跳转至编辑页面 
     * @param id 
     * @return 
     */  
    @RequestMapping(value="/edit/{id}")  
    public ModelAndView edit(@PathVariable int id) throws Exception{  
        User user=userService.getUserById(id);  
        return new ModelAndView("user/edit","user",user);  
    }  
    /** 
     * 编辑 
     * @param user 
     * @return 
     */  
    @RequestMapping(value="/edit")  
    public ModelAndView update(@Valid User user,BindingResult result)throws Exception{  
        ModelAndView view=new ModelAndView();  
        if(result.hasErrors()){  
            view.addObject("error", result.getAllErrors());  
            view.setViewName("user/edit");  
            return view;  
        }else{  
         userService.updateUser(user);  
         return new ModelAndView(LIST);  
        }  
    }
}