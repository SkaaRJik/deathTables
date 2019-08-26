package ru.filippov.neatvue.domain.death;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "death_data_total")
@Data
@SuperBuilder(toBuilder = true)
public class DeathDataTotal extends DeathData {


    /*@Column(name = "number_of_living_total")
    protected Integer numberOfLivingTotal;

    @OneToOne(mappedBy = "deathDataTotal")
    @JoinColumn(name = "fk_death_note_id")
    protected DeathNote deathNote;*/

}
