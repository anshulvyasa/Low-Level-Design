package org.lld.inventory_management_system.factory;

import org.lld.inventory_management_system.enums.ProductCategory;
import org.lld.inventory_management_system.models.products.Product;
import org.lld.inventory_management_system.models.products.contrete_product.ElectronicsProduct;
import org.lld.inventory_management_system.models.products.contrete_product.GroceryProduct;
import org.lld.inventory_management_system.models.products.contrete_product.UtilityProduct;

public class ProductFactory {
    public static Product getProduct(ProductCategory productCategory){
        return switch (productCategory){
            case GROCERY -> new GroceryProduct("Grocery01",productCategory,"Maggi",100,15,50);
            case ELECTRONICS -> new ElectronicsProduct("Electronic01",productCategory,"Mac",10,150000,5);
            case UTILITIES -> new UtilityProduct("Utility01",productCategory,"Lamp",1000,150,30);
        };
    }
}
