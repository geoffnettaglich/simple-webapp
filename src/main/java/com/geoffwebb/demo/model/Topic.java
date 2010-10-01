package com.geoffwebb.demo.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Topic
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany
    @Cascade(CascadeType.SAVE_UPDATE)
    private Collection<Suggestion> suggestions;
    
    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Collection<Suggestion> getSuggestions()
    {
        return suggestions;
    }

    public void setSuggestions(Collection<Suggestion> suggestions)
    {
        this.suggestions = suggestions;
    }
}
