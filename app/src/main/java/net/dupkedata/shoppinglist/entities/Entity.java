package net.dupkedata.shoppinglist.entities;

/**
 * Abstract class for all entities.
 */
public abstract class     Entity {

    private int mId;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }
}
