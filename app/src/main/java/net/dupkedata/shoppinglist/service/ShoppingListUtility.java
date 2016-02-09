package net.dupkedata.shoppinglist.service;

import net.dupkedata.shoppinglist.entities.ShoppingItem;
import net.dupkedata.shoppinglist.entities.ShoppingList;

import java.text.SimpleDateFormat;

/**
 * Utility class for {@link ShoppingList}
 */
public class ShoppingListUtility {

    public static int getNumberOfBoughtItems(ShoppingList shoppingList) {

        int numberOfBoughtItems = 0;

        for (ShoppingItem shoppingItem : shoppingList.getItems()) {
            if (shoppingItem.isBought()) numberOfBoughtItems++;
        }

        return numberOfBoughtItems;
    }

    public static boolean isShoppingListFinished(ShoppingList shoppingList) {
        int boughtItems = getNumberOfBoughtItems(shoppingList);

        return boughtItems == shoppingList.getItems().size();
    }

    public static String getFormattedCreatedAt(ShoppingList shoppingList) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

        return simpleDateFormat.format(shoppingList.getCreatedAt());
    }
}
