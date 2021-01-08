package ru.shakurov.mongo_hateoas.mongo_hateoas_example.repositories;

public interface BaseRepository<T, ID> {

    void save(T t);

    void delete(T t);

    T findById(ID _id);

    void update(T t);
}
