package com.LSG.dscommerce.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Entity
@Table(name = "tb_order")
public class Order {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE") // Hora bd UTC
    private Instant moment;
    @Setter
    @Column(name = "STATUS")
    private OrderStatus orderStatus;

    @Setter
    @ManyToOne
    @JoinColumn(name = "client_id") // chave estrangeira
    private User client;

    @Setter
    @OneToOne(mappedBy = "order" , cascade = CascadeType.ALL)
    private Payment payment;

    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> items = new HashSet<>();

    public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
        this.id = id;
        this.moment = moment;
        this.orderStatus = orderStatus;
        this.client = client;
    }


    public Order(){}

    public List<Product> getProducts(){
        return items.stream().map(OrderItem::getProduct).toList();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
