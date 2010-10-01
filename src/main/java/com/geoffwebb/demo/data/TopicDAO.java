package com.geoffwebb.demo.data;

import com.geoffwebb.demo.model.Topic;

public interface TopicDAO
{
    public Topic findById(Long id);
    public Topic save(Topic topic);
    public void delete(Topic topic);
}
