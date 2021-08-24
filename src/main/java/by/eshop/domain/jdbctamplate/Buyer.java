package by.eshop.domain.jdbctamplate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Date;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Buyer {

    private Long id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private Date birthDate;
    private String phone;
    private String email;
    private Integer postal_code;
    private String city;
    private String address;


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
