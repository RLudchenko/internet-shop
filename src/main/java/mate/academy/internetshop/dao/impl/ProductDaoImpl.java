package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.ProductDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Product;

@Dao
public class ProductDaoImpl implements ProductDao {

    @Override
    public Product create(Product product) {
        return Storage.addProduct(product);
    }

    @Override
    public Optional<Product> get(Long id) {
        return Storage.getProducts().stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Product> getAll() {
        return Storage.getProducts();
    }

    @Override
    public Product update(Product product) {
        Product updatedProduct = get(product.getId()).get();

        updatedProduct.setName(product.getName());
        updatedProduct.setPrice(product.getPrice());

        return updatedProduct;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.getProducts()
                .removeIf(product -> product.getId().equals(id));
    }
}
