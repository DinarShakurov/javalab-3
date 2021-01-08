package ru.shakurov.mongo_hateoas.mongo_hateoas_example.repositories.driver;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import ru.shakurov.mongo_hateoas.mongo_hateoas_example.domain.Product;
import ru.shakurov.mongo_hateoas.mongo_hateoas_example.repositories.BaseRepository;

@Repository
@PropertySource("classpath:db.properties")
public class BaseDriverRepositoryImpl implements BaseRepository<Product, String> {

    private final MongoCollection<Product> collection;

    @Value("${database.name}")
    private String dbName;

    public BaseDriverRepositoryImpl() {
        MongoClient mongoClient = MongoClients.create();
        MongoDatabase mongoDatabase = mongoClient.getDatabase(dbName);
        collection = mongoDatabase.getCollection("product", Product.class);
    }

    @Override
    public void save(Product product) {
        collection.insertOne(product);
    }

    @Override
    public Product findById(String _id) {
        Document filterById = new Document("_id", new ObjectId(_id));
        return collection.find(filterById).first();
    }

    @Override
    public void delete(Product product) {
        deleteById(product.get_id());
    }

    @Override
    public void update(Product product) {
        Document filterById = new Document("_id", new ObjectId(product.get_id()));
        collection.findOneAndReplace(filterById, product);
    }

    private void deleteById(String _id) {
        Document filterById = new Document("_id", new ObjectId(_id));
        collection.findOneAndDelete(filterById);
    }
}
