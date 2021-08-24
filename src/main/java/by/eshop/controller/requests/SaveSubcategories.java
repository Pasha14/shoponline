package by.eshop.controller.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveSubcategories {

    private Long categoryId;
    private List<String> subcategoryList;
}
