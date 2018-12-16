package cdi.afpa.api.repository;

import cdi.afpa.api.domain.Training;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Training entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {

    @Query(value = "select distinct training from Training training left join fetch training.teachers",
        countQuery = "select count(distinct training) from Training training")
    Page<Training> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct training from Training training left join fetch training.teachers")
    List<Training> findAllWithEagerRelationships();

    @Query("select training from Training training left join fetch training.teachers where training.id =:id")
    Optional<Training> findOneWithEagerRelationships(@Param("id") Long id);

}
