package pl.coderslab.charity.model.service.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import pl.coderslab.charity.model.dto.CategoryDTO;
import pl.coderslab.charity.model.entity.Category;
import pl.coderslab.charity.model.repository.CategoryRepository;

@DisplayName("Default Category Service Specification")
public class DefaultCategoryServiceTest {

    CategoryRepository categoryRepository;
    DefaultCategoryService categoryService;

    @BeforeEach
    void setUp() {
        categoryRepository = Mockito.mock(CategoryRepository.class);
        ModelMapper modelMapper = new ModelMapper();
        categoryService = new DefaultCategoryService(categoryRepository, modelMapper);
    }

    @DisplayName("Create new category")
    @Nested
    class CreateNewCategory {

        CategoryDTO categoryToCreate;
        Category categoryCreated;

        @BeforeEach
        void setUp() {
            categoryToCreate = new CategoryDTO();
            categoryToCreate.setName("test-name");

            categoryCreated = new Category();
            categoryCreated.setId(1L);
            categoryCreated.setName(categoryToCreate.getName());
        }

        @DisplayName(" - should save new category with repository")
        @Test
        public void test1() {
            Mockito.when(categoryRepository.save(ArgumentMatchers.any())).thenReturn(categoryCreated);

            categoryService.create(categoryToCreate);

            Mockito.verify(categoryRepository, Mockito.atLeastOnce()).save(ArgumentMatchers.any());
        }

        @DisplayName(" - should save new category with same name as provided")
        @Test
        public void test2() {
            ArgumentCaptor<Category> categoryCaptor = ArgumentCaptor.forClass(Category.class);
            Mockito.when(categoryRepository.save(categoryCaptor.capture())).thenReturn(categoryCreated);

            categoryService.create(categoryToCreate);

            Assertions.assertThat(categoryCaptor.getValue())
                    .isNotNull()
                    .hasFieldOrPropertyWithValue("name", categoryCreated.getName());
        }

    }

    @DisplayName("Find category by id")
    @Nested
    class FindCategoryById {

    }

}