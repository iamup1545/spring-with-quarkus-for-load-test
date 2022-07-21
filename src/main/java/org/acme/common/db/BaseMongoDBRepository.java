package org.acme.common.db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.core.http.HttpClient;
import io.vertx.mutiny.ext.web.client.WebClient;
import org.springframework.beans.factory.annotation.Value;

public abstract class BaseMongoDBRepository {

    @Value("${quarkus.mongodb.database}")
    protected String dbName;
    protected String dbCollectionName;

    protected MongoClient mongoClient;
    protected WebClient webClient;

    public BaseMongoDBRepository(MongoClient mongoClient, Vertx vertx) {
        this.mongoClient = mongoClient;
        this.initWebClient(vertx);
    }

    public void initWebClient(Vertx vertx) {
        WebClientOptions options = new WebClientOptions()
                .setDefaultHost("localhost")
                .setDefaultPort(4545);

        this.webClient = WebClient.create(vertx, options);
    }

    public abstract void setDBCollectionName();

    protected <T> MongoCollection<T> getCollection(Class<T> returnClass) {
        this.setDBCollectionName();
        return this.mongoClient.getDatabase(this.dbName)
                .getCollection(this.dbCollectionName, returnClass);
    }
}
