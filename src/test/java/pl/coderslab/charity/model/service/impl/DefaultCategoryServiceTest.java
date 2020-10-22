package pl.coderslab.charity.model.service.impl;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderslab.charity.model.dto.CategoryDTO;
import pl.coderslab.charity.model.entity.Category;
import pl.coderslab.charity.model.repository.CategoryRepository;
import pl.coderslab.charity.model.service.CategoryService;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCategoryServiceTest {
    @InjectMocks
    private DefaultCategoryService categoryService;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private ModelMapper modelMapper;

    /*@Test
    public void givenNewCategory_whenSave_ThenCategoryIsSaved() {
        String name = "Kat1";
        Category category = new Category();
        category.setId(1L);
        category.setName(name);

       when(categoryRepository.findByName(name)).thenReturn(category);

        CategoryDTO categoryDTO = categoryService.findByName(name);

        assertEquals(category.getName(), categoryDTO.getName());

    }
    */
    @Test
    public void givenNewCategory_whenSave_ThenCategoryIsReturnedCorrectly() {
        //given
        String name = "KAt2";
        Category category = new Category();
        category.setId(1L);
        category.setName(name);

        CategoryDTO categoryDTO = new CategoryDTO(1L, name);

        when(categoryRepository.save(category)).thenReturn(category);
        when(modelMapper.map(categoryDTO, Category.class)).thenReturn(category);
        //when
        Category result = categoryService.create(categoryDTO);
        //then
        assertNotNull(result);
        assertEquals(category.getName(),result.getName());



    }
}