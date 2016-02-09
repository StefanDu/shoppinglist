package net.dupkedata.shoppinglist.view.shoppinglistitems;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import net.dupkedata.shoppinglist.entities.ShoppingList;
import net.dupkedata.shoppinglist.model.DummyShoppingListRepository;
import net.dupkedata.shoppinglist.model.IShoppingListRepository;
import net.dupkedata.shoppinglist.shoppinglist.R;

/**
 * This activity represents a single shopping list showing its items.
 */
public class ShoppingListItemsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.shoppingList_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get Intent
        Intent intent = getIntent();
        int shoppingListId = intent.getIntExtra("ShoppingListId", -1);

        if (shoppingListId == -1)
            return;

        // Get Repository & ShoppingList
        IShoppingListRepository repository = DummyShoppingListRepository.getInstance();
        ShoppingList shoppingList = repository.getEntityById(shoppingListId);

        // Set Title
        setTitle(shoppingList.getTopic());

        // Create ListView
        ShoppingItemArrayAdapter shoppingItemArrayAdapter = new ShoppingItemArrayAdapter(this, shoppingList.getItems());
        final ListView shoppingItemListView = (ListView) findViewById(R.id.shoppingList_listView_shoppingItems);
        shoppingItemListView.setAdapter(shoppingItemArrayAdapter);

        // Add Listener to FloatingButton
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finishShoppingList();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        finishShoppingList();
    }

    private void finishShoppingList() {
        setResult(Activity.RESULT_OK);
        finish();
    }
}
