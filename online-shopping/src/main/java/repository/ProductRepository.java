package repository;

import model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product>findByBrandAndColorAndPrice(String brand, String color, Double price);

    List<Product> findByBrandAndColor(String s, String s1);

    List<Product> findByBrandAndPrice(String s, Double aDouble);

    List<Product> findByColorAndPrice(String s, Double aDouble);

    List<Product> findByBrand(String s);

    List<Product> findByColor(String s);

    List<Product> findByPrice(Double aDouble);
}
