package springframework.recipe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ingredient extends BaseEntity {

    private String description;
    private BigDecimal amount;

    @ManyToOne
    private Recipe recipe;

    // will be retrieved from db every time
    // lazy boots hibernate efficiency
    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure uom;

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
    }
}
