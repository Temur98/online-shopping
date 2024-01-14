package service;

import dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mapper.ProductMapper;
import model.Product;
import org.springframework.stereotype.Service;
import repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    public final ProductMapper productMapper;

    public List<ProductDTO> getAllProducts(Optional<String> brand, Optional<String> color, Optional<Double> price) {
        List<Product> products = new ArrayList<>();
        if (brand.isPresent() && color.isPresent() && price.isPresent()) {
            products = productRepository.findByBrandAndColorAndPrice(brand.get(), color.get(), price.get());
        } else if (brand.isPresent() && color.isPresent()) {
            products = productRepository.findByBrandAndColor(brand.get(), color.get());
        } else if (brand.isPresent() && price.isPresent()) {
            products = productRepository.findByBrandAndPrice(brand.get(), price.get());
        } else if (color.isPresent() && price.isPresent()) {
            products = productRepository.findByColorAndPrice(color.get(), price.get());
        } else if (brand.isPresent()) {
            products = productRepository.findByBrand(brand.get());
        } else if (color.isPresent()) {
            products = productRepository.findByColor(color.get());
        } else if (price.isPresent()) {
            products = productRepository.findByPrice(price.get());
        } else {
            products = productRepository.findAll();
        }
        return products.stream().map(
                productMapper::toDTO
        ).collect(Collectors.toList());
    }


    public ProductDTO getByIdProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Product not found"));
        return productMapper.toDTO(product);
    }



    public ProductDTO addProduct(ProductDTO productDTO) {
        Product save = productRepository.save(productMapper.toEntity(productDTO));
        return productMapper.toDTO(save);
    }


    public ProductDTO updateProduct(ProductDTO productDTO, Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Product not found")
        );

        Product entity = productMapper.toEntity(productDTO);
        product.setProductName(entity.getProductName());
        product.setBrand(entity.getBrand());
        product.setColor(entity.getColor());
        product.setQuantity(entity.getQuantity());
        product.setImageFile(entity.getImageFile());
        product.setPrice(entity.getPrice());

        return productMapper.toDTO(product);
    }


    public ProductDTO deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Product not found")
        );
        productRepository.delete(product);
        return productMapper.toDTO(product);
    }
}
