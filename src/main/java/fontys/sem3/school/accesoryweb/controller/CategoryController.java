package fontys.sem3.school.accesoryweb.controller;

import fontys.sem3.school.accesoryweb.business.GetCategoriesUseCase;
import fontys.sem3.school.accesoryweb.domain.GetCategoriesResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})
public class CategoryController {
    private final GetCategoriesUseCase getCategoriesUseCase;
    @GetMapping
    public ResponseEntity<GetCategoriesResponse> getCategories() {
        return ResponseEntity.ok(getCategoriesUseCase.getCategories());
    }

}

