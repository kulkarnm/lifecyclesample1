/*
 * Copyright (c) 2010-2016. Axon Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.shoppingapp.common.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import org.axonframework.mongo.eventsourcing.eventstore.DefaultMongoTemplate;
import org.axonframework.mongo.eventsourcing.eventstore.MongoTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;


@Configuration
public class MongoDBConfig {

    @Bean(name = "axonmongo")
    public MongoTemplate mongoTemplate(MongoClient mongo, @Value("${view.db.name}") String dbName) {
        MongoTemplate mongoTemplate =
                new DefaultMongoTemplate(mongo, dbName, "domainevents", "snapshotevents");
        return mongoTemplate;
    }

    @Bean
    public MongoClient mongo(MongoClientURI mongoClientURI) {
        return new MongoClient(mongoClientURI);
    }

    @Bean
    public MongoClientURI mongoClientURI(@Value("${view.db.host}") String host, @Value("${view.db.port}") int port,
                                         @Value("${view.db.name}") String dbName) {
        return new MongoClientURI("mongodb://" /*+ username + ":" + password + "@" */
                + host
                + ":"
                + port
                + "/" +
                dbName);
    }

    @Bean
    public MongoDbFactory mongoDbFactory(@Value("${view.db.host}") String host, @Value("${view.db.port}") int port,
                                         @Value("${view.db.name}") String dbName) {
        return new SimpleMongoDbFactory(new MongoClient(new ServerAddress(host, port)), dbName);
    }
}