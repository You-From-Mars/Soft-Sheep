package com.helen.softsheep;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClientURI;

 //等价于XML中配置bean
public class MongoDBConfig {
	
    @Value("${mongodb.schema}")
    private String databaseName = "";

    @Value("${mongodb.uri}")
    private String uri;

    @Value("${mongodb.username}")
    private String userName;

    @Value("${mongodb.password}")
    private String password;

    @Bean
    public MongoDbFactory mongoDbFactory() throws UnknownHostException {
        String uriStr="mongodb://"+userName+":"+password+"@"+uri+"/"+databaseName;
        System.out.println(uriStr);
        MongoClientURI mongoClientURI=new MongoClientURI(uriStr);
        MongoDbFactory mongoDbFactory=new SimpleMongoDbFactory(mongoClientURI);
        return mongoDbFactory;
    }
}
