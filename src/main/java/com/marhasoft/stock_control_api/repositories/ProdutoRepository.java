package com.marhasoft.stock_control_api.repositories;

import com.marhasoft.stock_control_api.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // Total products added in the current month
    @Query("SELECT COUNT(p) FROM Produto p WHERE FUNCTION('MONTH', p.createdDate) = :currentMonth AND FUNCTION('YEAR', p.createdDate) = :currentYear")
    Long countProductsAddedInCurrentMonth(@Param("currentMonth") int currentMonth, @Param("currentYear") int currentYear);

    // Total products added in the previous month
    @Query("SELECT COUNT(p) FROM Produto p WHERE FUNCTION('MONTH', p.createdDate) = :previousMonth AND FUNCTION('YEAR', p.createdDate) = :currentYear")
    Long countProductsAddedInPreviousMonth(@Param("previousMonth") int previousMonth, @Param("currentYear") int currentYear);

    // Total products added in December of the previous year if the current month is January
    @Query("SELECT COUNT(p) FROM Produto p WHERE FUNCTION('MONTH', p.createdDate) = 12 AND FUNCTION('YEAR', p.createdDate) = :previousYear")
    Long countProductsAddedInDecemberOfPreviousYear(@Param("previousYear") int previousYear);

}
