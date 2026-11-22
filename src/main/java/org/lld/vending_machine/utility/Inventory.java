package org.lld.vending_machine.utility;

import org.lld.vending_machine.products.Product;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private final Map<String,ItemShelf> shelfMap=new HashMap<>();

    public boolean addItemToShelf(String shelfId,int quantity){
        ItemShelf shelf=getShelf(shelfId);
        if(shelf==null) return false;

        shelf.increaseQuantityOfProduct(quantity);

        return true;
    }

    public boolean addItemToShelf(String shelfId,Product product){
         ItemShelf shelf=getShelf(shelfId);
         if(shelf==null) return  false;

         shelf.setProduct(product);

         return true;
    }

    private ItemShelf getShelf(String id){
        return  shelfMap.get(id);
    }

    public Product getProductByShelfIf(String id){
        return shelfMap.get(id).getProduct();
    }
}
