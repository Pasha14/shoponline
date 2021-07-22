package by.eshop.controller.requests;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiOperation("Class for creating user entity")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyerCreateRequest {
    public String login;
    public String password;
    public String name;
    public String surname;

}
