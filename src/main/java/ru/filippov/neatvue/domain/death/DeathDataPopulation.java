package ru.filippov.neatvue.domain.death;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "death_data_population")
@Entity
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class DeathDataPopulation extends DeathData{

    @Column(name = "number_of_living_city_dwellers")
    protected Integer numberOfLivingСityDwellers;

    @Column(name = "number_of_living_villagers")
    protected Integer numberOfLivingVillagers;

    @OneToOne(mappedBy = "deathDataPopulation")
    protected DeathNote deathNote;

    public DeathDataPopulation(Long id, @NotNull DataType dataType, Integer numberOfLivingСityDwellers, Integer numberOfLivingVillagers, DeathNote deathNote) {

        super(id, dataType);
        this.numberOfLivingСityDwellers = numberOfLivingСityDwellers;
        this.numberOfLivingVillagers = numberOfLivingVillagers;
        this.deathNote = deathNote;
    }
}
