package pl.app.user.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.app.user.domain.Product;

import java.time.LocalDate;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    @Modifying
    @Query(value = "Delete from PRODUCT where id = ?1", nativeQuery = true)
    void deleteById(Long id);

    @Query(value = "Select * from PRODUCT where start_date <= ?1 AND (end_date is null or  end_date >= ?1) limit 1", nativeQuery = true)
    Optional<Product> findTop1ByStartDateLessThanEqualAndEndDate(LocalDate now);

    @Query(value = "Select * from PRODUCT where start_date <= ?1 order by start_date desc limit 1", nativeQuery = true)
    Optional<Product> findTop1ByStartDateLessThanEqualAndEndDate2(LocalDate now);

    @Query("Select p from Product p where p.startDate <= ?1 AND (p.endDate is null or  p.endDate >= ?1)")
    Page<Product> findTop1ByStartDateLessThanEqualAndEndDate(LocalDate now, Pageable pageable);

    // szukam 1 rekordu gdzie: StartDate =< LocalDate.now() && (endDate = null || endDate => LocalDate.now())
}
