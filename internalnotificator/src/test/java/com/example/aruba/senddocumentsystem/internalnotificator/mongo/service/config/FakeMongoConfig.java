package com.example.aruba.senddocumentsystem.internalnotificator.mongo.service.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.net.InetSocketAddress;

@TestConfiguration
public class FakeMongoConfig {

    @Bean(destroyMethod = "shutdownNow")
    public MongoServer mongoServer() {
        return new MongoServer(new MemoryBackend());
    }

    @Bean
    public MongoClient mongoClient(MongoServer server) {
        InetSocketAddress serverAddress = server.bind();
        String uri = "mongodb://" + serverAddress.getHostName() + ":" + serverAddress.getPort();
        return MongoClients.create(uri);
    }
}