package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sun.security.acl.PermissionImpl;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

   @Autowired
   private IPermissionService permissionService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name ="page",required = true,defaultValue = "1") Integer page, @RequestParam(name ="size",required = true,defaultValue = "4" ) Integer size) throws Exception {
        ModelAndView mv =new ModelAndView();
        List<Permission> permissionList =permissionService.findAll(page,size);
        //PageInfo就是一个分页的BEAN
        PageInfo pageInfo =new PageInfo(permissionList);
        mv.addObject("permissionList",pageInfo);
        mv.setViewName("permission-list");
        return mv;
    }


    @RequestMapping("/save.do")
    public String save(Permission permission) throws Exception{
        permissionService.save(permission);
        return "redirect:findAll.do";
    }


    @RequestMapping("/findById")
    public ModelAndView findById(String id) throws Exception {
        Permission permission=  permissionService.findById(id);
        ModelAndView mv=new ModelAndView();
        mv.setViewName("permission-show");
        mv.addObject("permission",permission);
        return mv;
    }

    @RequestMapping("/deletePermission")
    public String deletePermission(String id) throws Exception {
        permissionService.deleteById(id);
        return "redirect:findAll.do";
    }

}
