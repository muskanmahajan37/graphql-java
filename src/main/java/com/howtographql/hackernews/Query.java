package com.howtographql.hackernews;


import com.coxautodev.graphql.tools.GraphQLRootResolver;
import com.howtographql.hackernews.Link;
import com.howtographql.hackernews.LinkRepository;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author julian
 */
public class Query implements GraphQLRootResolver{
    
    private final LinkRepository linkRepository;
    
    public Query(LinkRepository linkRepository){
        this.linkRepository = linkRepository;
    }
    
    public List<Link> allLinks(){
        return linkRepository.getAllLinks();
    }
    
}
