package com.geoffwebb.demo.controller;

import com.geoffwebb.demo.data.TopicDAO;
import com.geoffwebb.demo.model.Topic;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/topic.htm")
public class TopicController
{
    @Autowired
    private TopicDAO dao;
    
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
          dataBinder.setDisallowedFields(new String[] {"id"});
    }

    @RequestMapping(method=RequestMethod.GET)
    public String show(Model model)
    {
        List<Topic> topics= this.dao.findAll();
        model.addAttribute("topics", topics);
        return "topic";
    }

    public void setDao(TopicDAO dao) {
      this.dao = dao;
    }
}
