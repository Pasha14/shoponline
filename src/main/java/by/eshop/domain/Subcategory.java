package by.eshop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Subcategory {

    private Long id;
    private Integer categoryId;
    private String subcategory;

}
