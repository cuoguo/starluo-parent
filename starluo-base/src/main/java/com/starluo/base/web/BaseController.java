package com.starluo.base.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class BaseController {

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping("/")
    public ModelAndView index(){
        ModelAndView model = new ModelAndView("index");
        model.addObject("name","starluo-base");
        return model;
    }

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping("/hi")
    public ModelAndView hi(){
        ModelAndView model = new ModelAndView("hi");
        model.addObject("name","starluo-base");
        return model;
    }

}
