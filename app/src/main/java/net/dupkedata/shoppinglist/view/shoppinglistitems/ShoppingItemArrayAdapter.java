package net.dupkedata.shoppinglist.view.shoppinglistitems;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import net.dupkedata.shoppinglist.entities.ShoppingItem;
import net.dupkedata.shoppinglist.entities.ShoppingList;
import net.dupkedata.shoppinglist.service.ShoppingListUtility;
import net.dupkedata.shoppinglist.shoppinglist.R;

import java.util.List;

/**
 * Array adapter for {@Link ShoppingItem}
 */
public class ShoppingItemArrayAdapter extends ArrayAdapter<ShoppingItem> {
    private final Context mContext;
    private final List<ShoppingItem> mShoppingItems;

    public ShoppingItemArrayAdapter(Context context, List<ShoppingItem> shoppingItems) {
        super(context, -1, shoppingItems);
        mContext = context;
        mShoppingItems = shoppingItems;
    }

    @Override
    public int getCount() {
        return mShoppingItems.size();
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.shopping_item, parent, false);
        final CheckBox boughtCheckBox = (CheckBox) rowView.findViewById(R.id.soppingItem_checkBox_bought);
        final EditText descriptionEditText = (EditText) rowView.findViewById(R.id.soppingItem_editText_description);

        // Get shopping item at position
        final ShoppingItem shoppingItem = getItem(position);

        // Set bought
        boughtCheckBox.setChecked(shoppingItem.isBought());

        // Add bought change listener
        boughtCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                boolean isBought = boughtCheckBox.isChecked();
                shoppingItem.setBought(isBought);
            }
        });

        // Set description
        descriptionEditText.setText(shoppingItem.getDescription());

        // Add text change listener
        descriptionEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String description = descriptionEditText.getText().toString();
                shoppingItem.setDescription(description);
            }
        });

        return rowView;
    }
}
