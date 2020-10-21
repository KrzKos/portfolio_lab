package pl.coderslab.charity.model.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.dto.CategoryDTO;
import pl.coderslab.charity.model.entity.Category;
import pl.coderslab.charity.model.repository.CategoryRepository;
import pl.coderslab.charity.model.service.CategoryService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DefaultCategoryService implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

   /* @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;
*/
    @Override
    public void create(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        category.setName(categoryDTO.getName());
        categoryRepository.save(category);
    }

    @Override
    public List<CategoryDTO> findAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOS = categoryList.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());
        return categoryDTOS;
    }

    @Override
    public CategoryDTO findById(long id) {
        Optional<Category> categoryRepositoryById = categoryRepository.findById(id);
        if (categoryRepositoryById.isPresent()) {
            CategoryDTO categoryDTO = modelMapper.map(categoryRepositoryById.get(), CategoryDTO.class);
            return categoryDTO;
        }
        return null;
    }

    @Override
    public CategoryDTO findByName(String name) {
        Category category = categoryRepository.findByName(name);
        CategoryDTO categoryDTO = modelMapper.map(category,CategoryDTO.class);

        return categoryDTO;
    }

    @Override
    public void update(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        category.setId(categoryDTO.getId());
        categoryRepository.save(category);
    }

    @Override
    public void delete(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        Optional<Category> categoryRepositoryById = categoryRepository.findById(category.getId());

        categoryRepository.delete(categoryRepositoryById.get());

    }
}
