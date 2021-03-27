package com.itheima.ssm.controller;


import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Product;
import com.itheima.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;



    @RequestMapping("/findAll.do")
    @RolesAllowed("ADMIN")
    public ModelAndView findAll(@RequestParam(name ="page",required = true,defaultValue = "1") Integer page,@RequestParam(name ="size",required = true,defaultValue = "4" ) Integer size)throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> ps= productService.findAll(page,size);
        //PageInfo就是一个分页的BEAN
        PageInfo pageInfo =new PageInfo(ps);
        mv.addObject("productList", pageInfo);
        mv.setViewName("product-list1");
        return mv;

    }

    @RequestMapping("/save.do")
    public String save(Product product) throws Exception {
        productService.save(product);
        return "redirect:findAll.do";

    }

    @RequestMapping("/deleteProduct.do")
    public String deleteOrders( String id) throws Exception {
        productService.deleteById(id);
        return "redirect:findAll.do";
    }

}
