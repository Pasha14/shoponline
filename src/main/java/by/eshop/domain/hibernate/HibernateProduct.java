package by.eshop.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class HibernateProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "brand_id")
    private Long brandId;

    @Column
    private String name;

    @Column
    private String model;

    @Column
    private Integer price;

    @Column
    private Boolean availability;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    @JsonIgnoreProperties("id")
    private HibernateProductDescription description;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "category_id")
//    @JsonBackReference
////    @JsonIgnoreProperties("products") - для отображения объекта category в buyer
//    private HibernateSubcategory category;
}
