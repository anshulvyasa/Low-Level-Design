package org.lld.inventory_management_system.models.warehouse;

import lombok.Getter;
import org.lld.inventory_management_system.models.products.Product;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Warehouse {
     private final String warehouseId;
     private final Map<String, Product> productMap=new HashMap<>();

     public Warehouse(String warehouseId){
         this.warehouseId=warehouseId;
     }

     public void addProduct(Product product,int quantity){
         String sku=product.getSku();
         if(productMap.containsKey(sku)){
             Product product1=productMap.get(sku);
             product1.setQuantity(product1.getQuantity()+quantity);
         }
         else productMap.put(sku,product);
     }

     public void removeProduct(Product product,int quantity){
         String sku=product.getSku();
         if(!productMap.containsKey(sku)) return;

         Product product1=productMap.get(sku);
         if(product1.getQuantity()<quantity) return;;

         product1.setQuantity(product1.getQuantity()-quantity);
     }
}
