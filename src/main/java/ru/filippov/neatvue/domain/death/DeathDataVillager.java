package ru.filippov.neatvue.domain.death;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "death_data_villager")
@Entity
@Data
@SuperBuilder(toBuilder = true)
public class DeathDataVillager extends DeathData{

}
