package net.dupkedata.shoppinglist.view.shoppinglists;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import net.dupkedata.shoppinglist.entities.ShoppingList;
import net.dupkedata.shoppinglist.model.DummyShoppingListRepository;
import net.dupkedata.shoppinglist.model.IShoppingListRepository;
import net.dupkedata.shoppinglist.shoppinglist.R;
import net.dupkedata.shoppinglist.view.shoppinglistitems.ShoppingListItemsActivity;

import java.util.List;

/**
 * This activity shows a list of shoppinglists.
 */
public class ShoppingListsActivity extends AppCompatActivity {

    private IShoppingListRepository mRepository;
    private ShoppingListArrayAdapter mShoppingListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Floating button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewShoppingListDialogFragment dialog = new NewShoppingListDialogFragment();
                dialog.show(getSupportFragmentManager(), "new shoppinglist");
            }
        });

        // Introduce shopping lists
        mRepository = DummyShoppingListRepository.getInstance();
        List shoppingListList = mRepository.getEntitiesAsList();

        // Create list view
        mShoppingListAdapter = new ShoppingListArrayAdapter(this, shoppingListList);
        final ListView shoppingListListView = (ListView) findViewById(R.id.overview_listView_shoppingList);
        shoppingListListView.setAdapter(mShoppingListAdapter);
        shoppingListListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(view.getContext(), ShoppingListItemsActivity.class);
                ShoppingList selectedShoppingList = (ShoppingList) shoppingListListView.getItemAtPosition(position);
                intent.putExtra("ShoppingListId", selectedShoppingList.getId());
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            mShoppingListAdapter.notifyDataSetChanged();
        }
    }
}
