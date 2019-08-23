package ru.filippov.neatvue.domain.death;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "death_data_total")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class DeathDataTotal extends DeathData {


    @Column(name = "number_of_living_total")
    protected Integer numberOfLivingTotal;

    @OneToOne(mappedBy = "deathDataTotal")
    protected DeathNote deathNote;

    public DeathDataTotal(Long id, @NotNull DataType dataType, Integer numberOfLivingTotal, DeathNote deathNote) {
        super(id, dataType);
        this.numberOfLivingTotal = numberOfLivingTotal;
        this.deathNote = deathNote;
    }
}
