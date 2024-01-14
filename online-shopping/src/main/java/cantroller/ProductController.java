package cantroller;

import dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ProductService;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<List<ProductDTO>> getProduct(
                                                       @RequestParam Optional<String> brand,
                                                       @RequestParam Optional<String> color,
                                                       @RequestParam Optional<Double> price){
        return ResponseEntity.ok(productService.getAllProducts(brand, color, price));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getByIdProducts(@PathVariable Long id){
        return ResponseEntity.ok(productService.getByIdProduct(id));
    }

    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO){
        return ResponseEntity.ok(productService.addProduct(productDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO,
                                                    @PathVariable Long id){
        return ResponseEntity.ok(productService.updateProduct(productDTO,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable Long id){
        return ResponseEntity.ok(productService.deleteProduct(id));
    }
}
