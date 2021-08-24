package by.eshop.controller.requests;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiOperation("Class for creating order entity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateRequest {

    private Long productId;
    private Long  buyerId;
    private Integer quantity;
    private Long price;
    private String deliveryType;
    private String paymentType;
    private String comment;
}
