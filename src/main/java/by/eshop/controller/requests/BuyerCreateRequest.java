package by.eshop.controller.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BuyerCreateRequest {
    public String login;
    public String password;
    public String name;
    public String surname;

}
