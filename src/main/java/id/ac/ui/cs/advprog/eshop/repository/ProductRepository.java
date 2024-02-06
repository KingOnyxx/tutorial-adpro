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
        for (Product product : productData) {
            if (product.getProductId().equals(id)) {
                return product;
            }
        }
        return null;
    }
    
    public Iterator<Product> findAll(){
        return productData.iterator();
    }

    public Product edit(Product product, String id){
        Product editedProduct = findById(id);
        editedProduct.setProductName(product.getProductName());
        editedProduct.setProductQuantity(product.getProductQuantity());
        return editedProduct;
    }
    public void delete(String id){
        Product deletedProduct = findById(id);
        productData.remove(deletedProduct);
    }
}
