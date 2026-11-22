package org.lld.vending_machine.products;

import lombok.Getter;
import lombok.Setter;
import org.lld.inventory_management_system.enums.ProductCategory;

@Getter
public abstract class Product {
    private final ProductCategory productCategory;
    private final String name;
    @Setter private double price;
    private int quantity;


    public Product(ProductCategory productCategory,String name,double price){
        this.productCategory=productCategory;
        this.name=name;
        this.price=price;
    }

    public void addQuantity(int quantity){
        this.quantity += quantity;
    }
}
