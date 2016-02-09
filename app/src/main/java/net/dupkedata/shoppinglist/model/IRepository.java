package net.dupkedata.shoppinglist.model;

import net.dupkedata.shoppinglist.entities.Entity;
import net.dupkedata.shoppinglist.entities.ShoppingList;

import java.util.Collection;
import java.util.List;

/**
 * Interface for entity repositories.
 */
public interface IRepository<T extends Entity> {

    void updateEntity(T entity);

    T getEntityById(int id);

    void setEntities(Collection<T> entities);

    Collection<T> getEntities();

    List<T> getEntitiesAsList();
}
