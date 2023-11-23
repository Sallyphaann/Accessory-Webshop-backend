package fontys.sem3.school.accesoryweb.controller;

import fontys.sem3.school.accesoryweb.business.*;
import fontys.sem3.school.accesoryweb.configuration.security.isauthenticated.IsAuthenticated;
import fontys.sem3.school.accesoryweb.domain.*;
import fontys.sem3.school.accesoryweb.persistence.CategorySumQuality;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})
public class ProductController {
    private final CreateProductUseCase createProductUseCase;
    private final GetProductsUseCase getProductsUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final GetProductUsecase getProductUsecase;
    private final GetQuantityOfProductUseCase getQuantityOfProductUseCase;
    @GetMapping()
    public ResponseEntity<GetProductsResponse> getProducts() {
        return ResponseEntity.ok(getProductsUseCase.getProducts());
    }
    @GetMapping("{id}")
    public ResponseEntity<Product> getProduct(@PathVariable(value = "id") final long id) {
        final Optional<Product> productOptional = getProductUsecase.getProductId(id);
        if (productOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(productOptional.get());
    }
    @PostMapping()
    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<CreateProductResponse> createProduct(@RequestBody @Valid CreateProductRequest request) {
        CreateProductResponse response = createProductUseCase.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("{productId}")
    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<Void> deleteProduct(@PathVariable int productId) {
        deleteProductUseCase.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }
    @PutMapping("{id}")
    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<Void> updateProduct(@PathVariable("id") long id,
                                              @RequestBody @Valid UpdateProductRequest request) {
        request.setId(id);
        updateProductUseCase.updateProduct(request);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam("query") String query) {
        final List<Product> productList = getProductsUseCase.searchProducts(query);
        if (!productList.isEmpty()) {
            return ResponseEntity.ok(productList);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Product>> filterProductsByCategory(@RequestParam("category") String category) {
        if (category == null || category.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<Product> productList = getProductsUseCase.filterProductsByCategory(category);

        if (!productList.isEmpty()) {
            return ResponseEntity.ok(productList);
        }

        return ResponseEntity.notFound().build();
    }
    @GetMapping("static")
    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<List<CategorySumQuality>> getQuantityProduct(){
        List<CategorySumQuality> quantityProduct = getQuantityOfProductUseCase.getQuantityOfProductByCategory();
        return ResponseEntity.ok(quantityProduct);
    }





}
