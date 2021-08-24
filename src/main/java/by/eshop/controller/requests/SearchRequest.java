package by.eshop.controller.requests;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiOperation("Class for search request param")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequest {

    private String query;
}
