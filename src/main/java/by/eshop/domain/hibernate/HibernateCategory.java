package by.eshop.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "category_name")
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {
        "subcategory"
})
public class HibernateCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String category;

    @Column
    private String picture;

    @OneToMany(mappedBy = "categoryId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"categoryId", "products"})
    private Set<HibernateSubcategory> subcategory = Collections.emptySet();

}
