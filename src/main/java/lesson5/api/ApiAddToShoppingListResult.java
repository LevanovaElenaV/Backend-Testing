package lesson5.api;

import api.ApiSearchResultItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// POJO
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ApiAddToShoppingListResult {
    private Integer id;
    private String name;
    private List<MeasuresItem> measures;
    private Integer usages;
    private Integer usageRecipeIds;
    private boolean pantryItem;
    private String aisle;
    private double cost;

}
