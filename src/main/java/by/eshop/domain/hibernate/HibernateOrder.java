package by.eshop.domain.hibernate;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order")
@Data
@NoArgsConstructor
public class HibernateOrder {

    @Id
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "buyer_id")
    private Long buyerId;

    @Column
    private Integer quantity;

    @Column
    private Long price;

    @Column(name = "delivery_type")
    private String deliveryType;

    @Column(name = "payment_type")
    private String paymentType;

    @Column
    private String status;

    @Column
    private String comment;
}
