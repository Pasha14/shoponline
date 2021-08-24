package by.eshop.controller.requests;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@ApiOperation("Class for creating category entity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCreateRequest {

    private String category;

    private String picture;
}
