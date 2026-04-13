package uz.pdp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.enums.StepEnum;
import uz.pdp.enums.TypeEnum;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlusMinusEntity implements Serializable {

    private String id;
    private Long userId;

    private double amount;

    private TypeEnum type;

    private String comment;
    private StepEnum step;
}
