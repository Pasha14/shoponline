package by.eshop.controller.requests;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@ApiOperation("Class for creating buyer entity")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyerCreateRequest {
    private String login;
    private String password;
    private String name;
    private String surname;
    private LocalDateTime birthDate;
    private String phone;
    private String email;
    private Integer postalCode;
    private String city;
    private String address;

}
