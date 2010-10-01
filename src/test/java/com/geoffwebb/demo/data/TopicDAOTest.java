package com.geoffwebb.demo.data;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.geoffwebb.demo.data.TopicDAO;
import com.geoffwebb.demo.model.Suggestion;
import com.geoffwebb.demo.model.Topic;
import java.util.Collection;
import org.junit.After;

//@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class})
@ContextConfiguration(locations = {"classpath:spring/test-data.xml", "classpath:spring/test-dao.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TopicDAOTest
{
    @Autowired
    protected TopicDAO topicDao;

    @Test
    public void saveNewTopicShouldReturnATopicWithAnId()
    {
        Topic topic = new Topic();
        assertThat(topic.getId(), nullValue());
        
        topic = topicDao.save(topic);
        assertThat(topic.getId(), notNullValue());
    }

    
    @Test
    public void findNonExistingObjectShouldFailGracefully()
    {
        Topic topic = topicDao.findById(10l);
        assertThat(topic, nullValue());
    }
    
    @Test
    public void saveTopicWithSuggestionsShouldSaveAll()
    {
        Topic topic = new Topic();
        topic.setName("talks");
        
        Suggestion suggestion = new Suggestion();
        suggestion.setDescription("good stuff");
        
        List<Suggestion> suggestions = new ArrayList<Suggestion>();
        suggestions.add(suggestion);
        
        topic.setSuggestions(suggestions);
        
        Topic savedTopic = topicDao.save(topic);
        
        // once saved, topics should have a vlid ID ...
        assertThat(savedTopic.getId(), notNullValue());
        
        // and suggestions should all have real IDs too
        for(Suggestion sugg : savedTopic.getSuggestions())
        {
            assertThat(sugg.getId(), notNullValue());
        }
    }
    
    @Test
    public void saveThenDeleteShouldRemoveTheTopic()
    {
        Topic topic = new Topic();
        topic.setName("talk");
        topic = topicDao.save(topic);
        assertThat(topic.getId(), notNullValue());
        
        Long id = topic.getId();
        
        // make sure we can retrieve the saved item
        Topic savedTopic = topicDao.findById(id);
        assertThat(savedTopic, notNullValue());
        assertThat(savedTopic.getId(), is(id));
        
        // and that we can delete it too
        topicDao.delete(topic);
        
        savedTopic = topicDao.findById(id);
        assertThat(savedTopic, nullValue());
    }

    /**
     * TODO Bring this test back once setup and teardown work properly
     * @Test
     */
    public void gettingAnEmptyListOfTopicsShouldFailGracefully() {
        List<Topic> topics = topicDao.findAll();
        assertThat(topics.size(), equalTo(0));
    }

    /**
     * TODO Bring this test back once setup and teardown work properly
     * @Test
     */
    public void gettingAListOfTopics() {
        Topic topic1 = new Topic();
        topic1.setName("talk1");
        Topic topic2 = new Topic();
        topic1.setName("talk2");
        topicDao.save(topic1);
        topicDao.save(topic2);
        List<Topic> topics = topicDao.findAll();
        assertThat(topics, notNullValue());
        assertThat(topics.size(), equalTo(2));
    }
}
