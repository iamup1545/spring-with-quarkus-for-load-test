package org.acme.common.db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.springframework.beans.factory.annotation.Value;

public abstract class BaseMongoDBRepository {

    @Value("${quarkus.mongodb.database}")
    protected String dataBaseName;
    protected String dbCollectionName;

    protected MongoClient mongoClient;

    public BaseMongoDBRepository(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public abstract void setDBCollectionName();

    protected <T> MongoCollection<T> getCollection(Class<T> returnClass) {
        this.setDBCollectionName();
        return this.mongoClient.getDatabase(this.dataBaseName)
                .getCollection(this.dbCollectionName, returnClass);
    }
}
