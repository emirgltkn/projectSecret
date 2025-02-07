package com.project.NutritionApp.request;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.NutritionApp.dto.FoodLogDto;
import com.project.NutritionApp.entity.FoodLog;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateFoodLogRequest {

    List<FoodLogDto> meal;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate date;

}