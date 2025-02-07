package com.project.NutritionApp.repository;

import com.project.NutritionApp.entity.FoodLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface FoodLogRepository extends JpaRepository<FoodLog, Long> {

    @Query("SELECT f FROM FoodLog f WHERE f.user.userId = :userId AND DATE(f.createDate) = :createDate")
    List<FoodLog> findByUser_UserIdAndCreateDate(@Param("userId") Long userId, @Param("createDate") LocalDate createDate);

    List<FoodLog> findByUser_UserId(Long userId);
    void deleteById(Long foodId); // Bu metod JpaRepository tarafından otomatik sağlanır

    @Query("SELECT f FROM FoodLog f WHERE f.foodId = :foodId AND f.user.userId = :userId")
    Optional<FoodLog> findByUserAndFoodId(@Param("userId") Long userId, @Param("foodId") Long foodId);


}
