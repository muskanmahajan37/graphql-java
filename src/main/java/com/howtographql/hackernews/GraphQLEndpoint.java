/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.howtographql.hackernews;

import com.coxautodev.graphql.tools.SchemaParser;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author julian
 */
@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {
    
    private static final LinkRepository linkRepository;
    private static final UserRepository userRepository;
    
    static {
        MongoDatabase mongo = new MongoClient().getDatabase("hackernews");
        linkRepository = new LinkRepository(mongo.getCollection("links"));
        userRepository = new UserRepository(mongo.getCollection("users"));
    }

    public GraphQLEndpoint() {
        super(buildSchema());
    }
    
    private static GraphQLSchema buildSchema (){
        return SchemaParser.newParser()
                .file("schema.graphqls")
                .resolvers(new Query(linkRepository),new Mutation(linkRepository,userRepository),new SigninPayloadResolver())
                .build()
                .makeExecutableSchema();
    }
}
