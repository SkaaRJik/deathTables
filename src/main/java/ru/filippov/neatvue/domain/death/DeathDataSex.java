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

@Table(name = "death_data_sex")
@Entity
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class DeathDataSex extends DeathData{
    @Column(name = "number_of_living_male")
    protected Integer numberOfLivingMale;

    @Column(name = "number_of_living_female")
    protected Integer numberOfLivingFemale;

    @OneToOne(mappedBy = "deathDataSex")
    protected DeathNote deathNote;


    public DeathDataSex(Long id, @NotNull DataType dataType, int numberOfLivingMale, int numberOfLivingFemale, DeathNote deathNote  ){
        super(id, dataType);
        this.numberOfLivingMale = numberOfLivingMale;
        this.numberOfLivingFemale = numberOfLivingFemale;
        this.deathNote = deathNote;
    }

}
