package org.lld.inventory_management_system.models.products;

import lombok.Getter;
import lombok.Setter;
import org.lld.inventory_management_system.enums.ProductCategory;

@Getter
public abstract class Product {
    private final String sku;
    private final ProductCategory productCategory;
    private final String name;

    @Setter private double price;
    @Setter private  int quantity;
    @Setter private int threshold;

    public Product(String sku,ProductCategory productCategory,String name,int quantity,double price,int threshold){
        this.sku=sku;
        this.productCategory=productCategory;
        this.name=name;
        this.quantity=quantity;
        this.price=price;
        this.threshold=threshold;
    }
}
