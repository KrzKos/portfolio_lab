package pl.coderslab.charity.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.model.entity.Institution;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long> {
        @Query("select i from Institution i where i.name like %?1%")
        Institution findByName(String name);
}
