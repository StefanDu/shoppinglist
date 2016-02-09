package net.dupkedata.shoppinglist.entities;

import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;

import net.dupkedata.shoppinglist.shoppinglist.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Shopping list is a list of shopping items.
 */
public class ShoppingList extends Entity implements Parcelable {

    private String mTopic;
    private List<ShoppingItem> mItems;
    private Date mCreatedAt;

    public ShoppingList() {
        mTopic = "";
        mItems = new ArrayList<>();
        mCreatedAt = Calendar.getInstance().getTime();
    }

    public ShoppingList(String topic) {
        mTopic = topic;
        mItems = new ArrayList<>();
        mCreatedAt = Calendar.getInstance().getTime();
    }

    public String getTopic() {
        return mTopic;
    }

    public void setTopic(String topic) {
        mTopic = topic;
    }

    public Date getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(Date createdAt) {
        mCreatedAt = createdAt;
    }

    public List<ShoppingItem> getItems() {
        return mItems;
    }

    public void setItems(List<ShoppingItem> items) {
        mItems = items;
    }

    public void addItem(ShoppingItem item) {
        mItems.add(item);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTopic);
        dest.writeTypedList(mItems);
        dest.writeLong(mCreatedAt.getTime());
    }

    public static final Parcelable.Creator<ShoppingList> CREATOR = new Parcelable.Creator<ShoppingList>() {

        public ShoppingList createFromParcel(Parcel in) {
            return new ShoppingList(in);
        }

        public ShoppingList[] newArray(int size) {
            return new ShoppingList[size];
        }
    };

    private ShoppingList(Parcel in) {
        mTopic = in.readString();
        mItems = new ArrayList<>();
        in.readTypedList(mItems, ShoppingItem.CREATOR);
        mCreatedAt = new Date(in.readLong());
    }
}
