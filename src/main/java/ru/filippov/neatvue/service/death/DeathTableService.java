package ru.filippov.neatvue.service.death;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.filippov.neatvue.domain.death.DeathNote;
import ru.filippov.neatvue.repository.DeathNoteRepository;
import ru.filippov.neatvue.service.death.table.DeathTable;
import ru.filippov.neatvue.service.death.table.DeathTableCreator;

import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DeathTableService {

    @Autowired
    private DeathNoteRepository deathNoteRepository;

    @Transactional
    public void addDeathNoteInfo(DeathNote newDeathNote){

        deathNoteRepository.save(newDeathNote);

    }


    public List<DeathNote> getDeathNotesByBirthYearAndBetweenAges(short birthYear,
                                                                  byte ageFrom,
                                                                  byte ageTo) throws SQLDataException {
        return deathNoteRepository
                .findAllByBirthYearAndAgeBetween(birthYear, ageFrom, ageTo)
                .orElseThrow(() -> new SQLDataException("Нет соответсвий данному запросу"));
    }



    public DeathTable getSexDeathTableByBirthAge(short birthYear) throws SQLDataException {

        List<DeathNote> deathNotes = deathNoteRepository
                .findAllByBirthYear(birthYear)
                .orElseThrow(() -> new SQLDataException("Нет соответсвий данному запросу"));

            return DeathTableCreator.createTableForOneYearAndSexCategory(deathNotes);
    }

    public List<DeathNote> getDeathTableOnYearBetweenAges(short year,
                                                     byte ageFrom,
                                                     byte ageTo) throws SQLDataException {

        List<DeathNote> deathNotes = deathNoteRepository
                .findAllByBirthYearBetweenAndAgeBetween((short) (year-ageTo), (short) (year - ageFrom), ageFrom, ageTo)
                .orElseThrow(()-> new SQLDataException("Нет соответсвий данному запросу"));

        byte currentAge = ageFrom;

        List<DeathNote> result = new ArrayList<>(ageTo-ageFrom);

        for (byte i = ageFrom; i < ageTo; i++) {
            for(DeathNote deathNote : deathNotes){
                if(deathNote.getAge() == i && deathNote.getBirthYear() == year - i){
                    result.add(deathNote);
                }
            }
        }

        return result;



    }
}
