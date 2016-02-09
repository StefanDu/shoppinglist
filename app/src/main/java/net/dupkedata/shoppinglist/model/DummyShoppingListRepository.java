package net.dupkedata.shoppinglist.model;

import net.dupkedata.shoppinglist.entities.ShoppingItem;
import net.dupkedata.shoppinglist.entities.ShoppingList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Only for testing!
 * Dummy repository for {@link ShoppingList}.
 */
public class DummyShoppingListRepository implements IShoppingListRepository {

    private static final DummyShoppingListRepository repository = new DummyShoppingListRepository();

    public static DummyShoppingListRepository getInstance() {
        return repository;
    }

    private Map<Integer, ShoppingList> mShoppingListMap;

    public Map<Integer, ShoppingList> getShoppingListMap() {
        return mShoppingListMap;
    }

    public void setShoppingListMap(Map<Integer, ShoppingList> shoppingListMap) {
        mShoppingListMap = shoppingListMap;
    }

    public DummyShoppingListRepository() {
        mShoppingListMap  = new HashMap<Integer, ShoppingList>(){{
            ShoppingList dummy = new ShoppingList("Netto");
            dummy.setId(0);
            ShoppingItem dummyItem = new ShoppingItem("Fettarme H-Milch");
            ShoppingItem dummyItem2 = new ShoppingItem("Bio-Tomaten");
            ShoppingItem dummyItem3 = new ShoppingItem("Gurke");
            ShoppingItem dummyItem4 = new ShoppingItem("Eine Packung Frischkäse");
            dummy.addItem(dummyItem);
            dummy.addItem(dummyItem2);
            dummy.addItem(dummyItem3);
            dummy.addItem(dummyItem4);
            put(dummy.getId(), dummy);

            ShoppingList dummy2 = new ShoppingList("Rewe");
            dummy2.setId(1);
            dummyItem = new ShoppingItem("Butter (Süßrahm)");
            dummyItem.setBought(true);
            dummyItem2 = new ShoppingItem("Vollkornbrot");
            dummyItem2.setBought(true);
            dummy2.addItem(dummyItem);
            dummy2.addItem(dummyItem2);
            put(dummy2.getId(), dummy2);
        }};
    }

    @Override
    public void updateEntity(ShoppingList entity) {

        mShoppingListMap.put(entity.getId(), entity);
    }

    @Override
    public ShoppingList getEntityById(int id) {

        return mShoppingListMap.get(id);
    }

    @Override
    public void setEntities(Collection<ShoppingList> entities) {

        mShoppingListMap = new HashMap<>();
        for (ShoppingList entity : entities) {
            mShoppingListMap.put(entity.getId(), entity);
        }
    }

    @Override
    public Collection<ShoppingList> getEntities() {

        return mShoppingListMap.values();
    }

    @Override
    public List<ShoppingList> getEntitiesAsList() {

        Collection<ShoppingList> shoppingLists = mShoppingListMap.values();
        return new ArrayList<>(shoppingLists);
    }
}
