package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product){
        productData.add(product);
        product.setProductId(String.valueOf(productData.size()));
        return product;
    }

    public Product findById(String id){
        int index = Integer.parseInt(id);
        if (index <= productData.size()){
            int min = 0;
            int max = productData.size() - 1;
            while (min <= max) {
                int mid = (min + max) / 2;
                if (index > Integer.parseInt(productData.get(mid).getProductId())) {
                    min = mid + 1;
                } else if (index < Integer.parseInt(productData.get(mid).getProductId())) {
                    max = mid - 1;
                } else {
                    return productData.get(mid);
                }
            }
        }
        return null;
    }
    
    public Iterator<Product> findAll(){
        return productData.iterator();
    }

    public Product edit(Product product, String id){
        Product editedProduct = productData.get(Integer.parseInt(id)-1);
        editedProduct.setProductName(product.getProductName());
        editedProduct.setProductQuantity(product.getProductQuantity());
        return editedProduct;
    }
}
