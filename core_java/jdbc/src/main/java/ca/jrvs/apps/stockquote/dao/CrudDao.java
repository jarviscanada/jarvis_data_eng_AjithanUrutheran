package ca.jrvs.apps.stockquote.dao;


import java.util.Optional;

public interface CrudDao<T, ID> {

    /**
     * Saves a given entity. Used for create and update
     * @param entity - must not be null
     * @return The saved entity. Will never be null
     * @throws IllegalArgumentException - if id is null
     */
    T save(T entity) throws IllegalArgumentException;

    /**
     * Retrieves an entity by its id
     * @param id - must not be null
     * @return Entity with the given id or empty optional if none found
     * @throws IllegalArgumentException - if id is null
     */
    Optional<T> findById(ID id) throws IllegalArgumentException;

    /**
     * Retrieves all entities
     * @return All entities
     */
    Iterable<T> findAll();

    /**
     * Deletes the entity with the given id. If the entity is not found, it is silently ignored
     * @param id - must not be null
     * @throws IllegalArgumentException - if id is null
     */
    void deleteById(ID id) throws IllegalArgumentException;

    /**
     * Deletes all entities managed by the repository
     */
    void deleteAll();

}
