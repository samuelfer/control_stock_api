package com.marhasoft.stock_control_api.repositories;

import com.marhasoft.stock_control_api.models.Ordem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdemRepository extends JpaRepository<Ordem, Long> {

    // Total orders added in the current month
    @Query("SELECT COUNT(o) FROM Ordem o WHERE FUNCTION('MONTH', o.createdDate) = :currentMonth AND FUNCTION('YEAR', o.createdDate) = :currentYear")
    Long countOrdersAddedInCurrentMonth(@Param("currentMonth") int currentMonth, @Param("currentYear") int currentYear);

    // Total orders added in the previous month
    @Query("SELECT COUNT(o) FROM Ordem o WHERE FUNCTION('MONTH', o.createdDate) = :previousMonth AND FUNCTION('YEAR', o.createdDate) = :currentYear")
    Long countOrdersAddedInPreviousMonth(@Param("previousMonth") int previousMonth, @Param("currentYear") int currentYear);

    // Total orders added in December of the previous year if the current month is January
    @Query("SELECT COUNT(o) FROM Ordem o WHERE FUNCTION('MONTH', o.createdDate) = 12 AND FUNCTION('YEAR', o.createdDate) = :previousYear")
    Long countOrdersAddedInDecemberOfPreviousYear(@Param("previousYear") int previousYear);

}
