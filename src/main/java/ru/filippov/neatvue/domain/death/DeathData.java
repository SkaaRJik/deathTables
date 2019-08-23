package ru.filippov.neatvue.domain.death;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeathData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "data_type")
    @NotNull
    @Enumerated(EnumType.STRING)
    protected DataType dataType;
}
