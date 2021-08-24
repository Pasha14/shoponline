package by.eshop.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
//@EqualsAndHashCode(exclude = {
//        "products"
//})
public class HibernateSubcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_id")
    private Long categoryId;

    @Column
    private String subcategory;

//    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JsonManagedReference
////    @JsonIgnoreProperties("category")
//    private Set<HibernateProduct> products = Collections.emptySet();
}
