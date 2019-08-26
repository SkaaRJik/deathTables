package ru.filippov.neatvue.domain.death;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "death_data_city_dwellers")
@Entity
@Data
@SuperBuilder(toBuilder = true)
public class DeathDataCityDweller extends DeathData{

}
