package io.github.Brettyuchoa.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Account {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ACCOUNT")
    @SequenceGenerator(sequenceName = "S_ACCOUNT", allocationSize = 1, name = "S_ACCOUNT")
    @Id
    @NotNull
    @Min(value = 1, message = "Number deve ser um valor positivo.")
    private Long number;

    @NotNull
    @Min(value = 1, message = "CodigoUser deve ser um valor positivo.")
    private Long codigoUser;

    @NotNull
    private Double balance;

    public void debit(double amount) {
    }

    // ... outros métodos
}