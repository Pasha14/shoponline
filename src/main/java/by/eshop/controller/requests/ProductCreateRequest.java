package by.eshop.controller.requests;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@ApiOperation("Class for creating product entity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateRequest {

    private Long categoryId;

    private Long brandId;

    private String name;

    private String model;

    private Integer price;

    private Boolean availability;
}
