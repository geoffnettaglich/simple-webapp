package com.geoffwebb.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/")
public class TopicController
{
    @RequestMapping(method=RequestMethod.GET)
    public String show() 
    {
        return "index";
    }
}
