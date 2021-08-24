package by.eshop.domain.hibernate;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "brand")
@Data
@NoArgsConstructor
public class HibernateBrand {

    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String logo;

}
