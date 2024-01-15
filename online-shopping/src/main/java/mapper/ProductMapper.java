package mapper;

import dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import entity.Product;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    public Product toEntity(ProductDTO productDTO){
        return productDTO == null ? null : new Product(
                productDTO.getId(),
                productDTO.getProductName(),
                productDTO.getQuantity(),
                productDTO.getBrand(),
                productDTO.getColor(),
                productDTO.getImageFile(),
                productDTO.getPrice()
        );
    }

    public ProductDTO toDTO(Product product){
        return product == null ? null : new ProductDTO(
                product.getId(),
                product.getProductName(),
                product.getQuantity(),
                product.getImageFile(),
                product.getColor(),
                product.getImageFile(),
                product.getPrice()
        );
    }
}
