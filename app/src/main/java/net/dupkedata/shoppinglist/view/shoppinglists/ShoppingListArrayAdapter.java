package net.dupkedata.shoppinglist.view.shoppinglists;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import net.dupkedata.shoppinglist.entities.ShoppingList;
import net.dupkedata.shoppinglist.service.ShoppingListUtility;
import net.dupkedata.shoppinglist.shoppinglist.R;

import java.util.List;

/**
 * Array adapter for {@link ShoppingList}
 */
public class ShoppingListArrayAdapter extends ArrayAdapter<ShoppingList> {
    private final Context mContext;
    private final List<ShoppingList> mShoppingLists;

    public ShoppingListArrayAdapter(Context context, List<ShoppingList> shoppingLists) {
        super(context, -1, shoppingLists);
        mContext = context;
        mShoppingLists = shoppingLists;
    }

    @Override
    public int getCount() {
        return mShoppingLists.size();
    }

    @Override
    @SuppressWarnings("Need deprecated for min api version")
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.shopping_list, parent, false);
        TextView topicTextView = (TextView) rowView.findViewById(R.id.ListView_ShoppingList_TextView_Topic);
        TextView dateTextView = (TextView) rowView.findViewById(R.id.ListView_ShoppingList_TextView_Date);
        TextView itemsTextView = (TextView) rowView.findViewById(R.id.ListView_ShoppingList_TextView_Items);

        ShoppingList shoppingList = mShoppingLists.get(position);

        // Set topic
        topicTextView.setText(shoppingList.getTopic());

        // Set date
        dateTextView.setText(ShoppingListUtility.getFormattedCreatedAt(shoppingList));

        // Set items
        String itemsLabel = mContext.getString(R.string.shoppingList_NumberOfBoughtItems) + ShoppingListUtility.getNumberOfBoughtItems(shoppingList) + "/" + shoppingList.getItems().size();
        itemsTextView.setText(itemsLabel);

        // Set background color depending on the list status
        if (ShoppingListUtility.isShoppingListFinished(shoppingList)) {

            rowView.setBackgroundColor(mContext.getResources().getColor(R.color.ListItemFinished));
        }

        return rowView;
    }
}
