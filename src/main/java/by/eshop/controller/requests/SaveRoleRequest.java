package by.eshop.controller.requests;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiOperation("Class for creating role entity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveRoleRequest {

    private String roleName;
}
