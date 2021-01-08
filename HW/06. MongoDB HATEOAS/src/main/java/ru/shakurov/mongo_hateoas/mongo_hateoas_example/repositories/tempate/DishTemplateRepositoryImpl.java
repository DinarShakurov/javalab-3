package ru.shakurov.mongo_hateoas.mongo_hateoas_example.repositories.tempate;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ru.shakurov.mongo_hateoas.mongo_hateoas_example.domain.Dish;
import ru.shakurov.mongo_hateoas.mongo_hateoas_example.repositories.BaseRepository;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
public class DishTemplateRepositoryImpl implements BaseRepository<Dish, String> {

    @Autowired
    private MongoTemplate template;

    @Override
    public void save(Dish dish) {
        template.insert(dish);
    }

    @Override
    public void delete(Dish dish) {
        template.remove(dish);
    }

    @Override
    public Dish findById(String _id) {
        return template.findById(_id, Dish.class);
    }

    @Override
    public void update(Dish dish) {
        template.findAndReplace(
                new Query(where("_id").is(new ObjectId(dish.get_id()))),
                dish
        );
    }
}
