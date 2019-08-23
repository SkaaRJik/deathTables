package ru.filippov.neatvue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.filippov.neatvue.domain.death.DeathNote;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeathNoteRepository extends JpaRepository<DeathNote, Long> {
    Optional<List<DeathNote>> findAllByAge( byte age);
    Optional<List<DeathNote>> findAllByAgeBetween( byte ageFrom, byte ageTo);
    Optional<List<DeathNote>> findAllByBirthYearAndAgeBetween(short birthYear, byte ageFrom, byte ageTo);
    Optional<List<DeathNote>> findAllByBirthYear( short birthYear);
    Optional<List<DeathNote>> findAllByBirthYearBetween( short birthYearFrom, short birthYearTo);
    Optional<DeathNote> findByBirthYearAndAge(short birthYear, byte age);
}
