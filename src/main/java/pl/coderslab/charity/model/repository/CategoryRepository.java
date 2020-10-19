package pl.coderslab.charity.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.model.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
