package ru.filippov.neatvue.domain.death;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CollectionType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "death_note")
@Entity
@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeathNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "birth_year")
    @NotNull
    protected Short birthYear;

    @NotNull
    protected Byte age;



    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    protected Country country;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="death_data_total_id")
    protected DeathDataTotal deathDataTotal;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="death_data_sex_id")
    protected DeathDataSex deathDataSex;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="death_data_population_id")
    protected DeathDataPopulation deathDataPopulation;



}
