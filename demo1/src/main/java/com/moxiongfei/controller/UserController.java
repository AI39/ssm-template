package com.moxiongfei.controller;

import com.moxiongfei.bean.User;
import com.moxiongfei.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/user/login")
    public ModelAndView login(User user) {
        ModelAndView modelAndView = new ModelAndView();
        //调用service层处理
        User loginUser = userService.login(user);
        if (loginUser != null) {
            modelAndView.addObject("user", loginUser);
            modelAndView.setViewName("loginSuccess");;;
        }
        else {
            modelAndView.setViewName("loginError");
        }
        return modelAndView;
    }

    @RequestMapping("/user/regist")
    public ModelAndView regist(User user) {
        ModelAndView modelAndView = new ModelAndView();
        //调用service层处理
        int i = userService.regist(user);
        if (i == 0) {
            modelAndView.setViewName("registSuccess");;
        }
        else {
            modelAndView.setViewName("registError");
        }
        return modelAndView;
    }
    /*测试处理器，即数据转换器*/
    @RequestMapping("/user/test")
    public String testConverter(Date date) {
        System.out.println(date);
        return "loginSuccess";
    }
    /*测试拦截器*/
    @RequestMapping("/user/auth/test")
    public String testInterceptor() {
        return "loginSuccess";
    }
    /*测试文件上传*/
    @RequestMapping("/user/fileUpload")
    public ModelAndView fileUpload(HttpServletRequest req,  MultipartFile headico) {
        ModelAndView modelAndView = new ModelAndView();

        String uploadPath = req.getServletContext().getRealPath("upload");     //将所有上传的文件都保存在upload/images目录下
        uploadPath += "/img";
        String fileName = headico.getOriginalFilename();
        String realPath = uploadPath + "/" + fileName;                         //获取图片的绝对路径，即在服务器上的存储路径
        String imgurl = realPath.substring(realPath.indexOf("upload"));        //获取图片的存储路径，这是相对于webapp根目录下的路径
        //user.setImgurl(imgurl);                                                //将图片的存储路径封装到对象user
        System.out.println(imgurl);                                            //打印图片的存储路径，只在测试时使用
        File file = new File(realPath);                                        //创建文件，准备上传文件
        if(!file.getParentFile().exists()){                                    //如果保存文件的父目录不存在，则创建该目录
            file.getParentFile().mkdirs();
        }
        try {
            headico.transferTo(file);                                          //文件上传
        } catch (IOException e) {
            e.printStackTrace();
        }

        modelAndView.setViewName("loginSuccess");
        return modelAndView;
    }
}
