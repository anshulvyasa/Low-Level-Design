package org.lld.vending_machine.utility;

import lombok.Getter;
import lombok.Setter;
import org.lld.vending_machine.products.Product;

@Getter
public class ItemShelf {
    private final String id;
    @Setter
    private Product product;

    public ItemShelf(String id){
        this.id=id;
    }

    public void increaseQuantityOfProduct(int quantity){
        product.addQuantity(quantity);
    }

    public void changeProduct(Product product){
        this.product=product;
    }
}
