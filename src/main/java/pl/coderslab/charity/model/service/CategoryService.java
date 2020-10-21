package pl.coderslab.charity.model.service;

import pl.coderslab.charity.model.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    void create(CategoryDTO categoryDTO);
    void update(CategoryDTO categoryDTO);
    List<CategoryDTO> findAllCategories();
    CategoryDTO findById(long id);
    CategoryDTO findByName(String name);
    void delete(CategoryDTO categoryDTO);
}
