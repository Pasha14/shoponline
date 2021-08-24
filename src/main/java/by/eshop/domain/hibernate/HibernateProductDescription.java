package by.eshop.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "product_description")
@Data
@NoArgsConstructor
public class HibernateProductDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer year;

    @Column
    private String color;

    @Column
    private Integer weight;

    @OneToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private HibernateProduct product;



}
