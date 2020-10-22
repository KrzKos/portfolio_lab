package pl.coderslab.charity.model.service;

import pl.coderslab.charity.model.dto.CategoryDTO;
import pl.coderslab.charity.model.entity.Category;

import java.util.List;

public interface CategoryService {

    Category create(CategoryDTO categoryDTO);
    void update(CategoryDTO categoryDTO);
    List<CategoryDTO> findAllCategories();
    CategoryDTO findById(long id);
    CategoryDTO findByName(String name);
    void delete(CategoryDTO categoryDTO);
}
