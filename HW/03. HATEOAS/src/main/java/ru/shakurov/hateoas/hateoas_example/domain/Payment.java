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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "PAYMENT")
@AttributeOverride(name = "id", column = @Column(name = "PAYMENT_ID"))
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class Payment extends LongIdEntity {

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    private Account payer;

    @OneToOne
    @JoinColumn(name = "FOOD_ORDER_ID", nullable = false)
    private FoodOrder foodOrder;

    @Column(name = "COST", nullable = false)
    private Long cost;

    @Column(name = "CREATE_TIME", nullable = false)
    private Date createTime;
}
