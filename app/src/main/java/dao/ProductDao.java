package dao;

import java.util.List;

import model.Product;

public interface ProductDao {
    boolean insertProduct(Product product);
    boolean updateProduct(Product product);
    boolean deleteProduct(Product product);
    List<Product> getAllProduct();
}
