package ru.shakurov.hateoas.hateoas_example.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.shakurov.hateoas.hateoas_example.domain.common.LongIdEntity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "FOOD_ORDER")
@AttributeOverride(name = "id", column = @Column(name = "FOOD_ORDER_ID"))
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class FoodOrder extends LongIdEntity {

    @OneToMany
    @JoinTable(
            name = "FOOD_ORDER_DISH",
            joinColumns = @JoinColumn(name = "FOOD_ORDER_ID"),
            inverseJoinColumns = @JoinColumn(name = "DISH_ID")
    )
    private Set<Dish> dishes = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    private Account customer;

    @Column(name = "CREATE_TIME", nullable = false)
    private Date createTime;
}
