package pl.coderslab.charity.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.dto.CategoryDTO;
import pl.coderslab.charity.model.service.CategoryService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryApiController {
    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryDTO> showAllCategories() {
        return categoryService.findAllCategories();
    }

    @PostMapping
    public ResponseEntity saveCategory(@Valid @RequestBody CategoryDTO category) {
        return null;
    }
}
