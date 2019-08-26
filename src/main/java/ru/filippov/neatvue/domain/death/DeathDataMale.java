package ru.filippov.neatvue.domain.death;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "death_data_male")
@Entity
@Data
@SuperBuilder(toBuilder = true)
public class DeathDataMale extends DeathData{


}
