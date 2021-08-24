package by.eshop.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "buyer")
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {
        "roles"
})
@NamedQuery(name = "HibernateBuyer_findAll",
query = "SELECT b FROM HibernateBuyer b ORDER BY b.id ASC")
public class HibernateBuyer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String surname;

    @Column(name = "birth_date")
    private LocalDateTime birthDate;

    @Column
    private String phone;

    @Column
    private String email;

    @Column(name = "postal_code")
    private Integer postalCode;

    @Column
    private String city;

    @Column
    private String address;

    @ManyToMany(mappedBy = "buyers", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("buyers")
    private Set<HibernateRole> roles = Collections.emptySet();
}
