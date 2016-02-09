package net.dupkedata.shoppinglist.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Entity for a shopping item.
 */
public class ShoppingItem extends Entity implements Parcelable {

    private String mDescription;
    private boolean mIsBought;

    public ShoppingItem() {
        mDescription = "";
    }

    public ShoppingItem(String description) {
        mDescription = description;
        mIsBought = false;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String item) {
        mDescription = item;
    }

    public boolean isBought() {
        return mIsBought;
    }

    public void setBought(boolean bought) {
        this.mIsBought = bought;
    }

    @Override
    public String toString() {
        return getDescription();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mDescription);
        dest.writeValue(mIsBought);
    }

    public static final Parcelable.Creator<ShoppingItem> CREATOR = new Parcelable.Creator<ShoppingItem>() {

        public ShoppingItem createFromParcel(Parcel in) {
            return new ShoppingItem(in);
        }

        public ShoppingItem[] newArray(int size) {
            return new ShoppingItem[size];
        }
    };

    private ShoppingItem(Parcel in) {
        mDescription = in.readString();
        mIsBought = (Boolean) in.readValue(null);
    }
}
