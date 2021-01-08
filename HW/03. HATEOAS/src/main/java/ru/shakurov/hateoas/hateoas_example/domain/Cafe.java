package ru.shakurov.hateoas.hateoas_example.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import ru.shakurov.hateoas.hateoas_example.domain.common.LongIdEntity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CAFE")
@AttributeOverride(name = "id", column = @Column(name = "CAFE_ID"))
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cafe extends LongIdEntity {

    @Column(name = "NAME", nullable = false)
    private String name;

    @OneToMany(mappedBy = "cafe")
    @Cascade({CascadeType.SAVE_UPDATE})
    private Set<Dish> dishes = new HashSet<>();
}
