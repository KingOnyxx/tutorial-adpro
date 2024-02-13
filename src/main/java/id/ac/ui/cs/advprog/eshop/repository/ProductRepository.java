package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product){
        productData.add(productData.size(), product);
        product.setProductId(product.getProductId() == null ? String.valueOf(UUID.randomUUID()) : product.getProductId());
        return product;
    }

    public int findById(String id){
        for (Product product : productData) {
            if (product.getProductId().equals(id)) {
                return productData.indexOf(product);
            }
        }
        return 0;
    }

    public Product findProductById(String id){
        return productData.get(findById(id));
    }
    
    public Iterator<Product> findAll(){
        return productData.iterator();
    }

    public Product edit(Product product, String id){
        Product editedProduct = findProductById(id);
        editedProduct.setProductName(product.getProductName());
        editedProduct.setProductQuantity(product.getProductQuantity());
        return editedProduct;
    }
    public void delete(String id){
        Product deletedProduct = findProductById(id);
        productData.remove(deletedProduct);
    }
}
