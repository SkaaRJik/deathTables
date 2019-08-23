package ru.filippov.neatvue.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.filippov.neatvue.service.death.DeathTableService;
import ru.filippov.neatvue.service.death.table.DeathTable;

import java.io.IOException;
import java.sql.SQLDataException;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/public/death_table")
@Slf4j
public class DeathTableRestAPI {

    @Autowired
    DeathTableService deathTableService;

    @GetMapping
    public ResponseEntity<?> getDeathTable(@RequestParam Map<String, String> allParams){


        try {
            Map<String,Object> result =
                    new ObjectMapper().readValue(allParams.values().toString().substring(1, allParams.values().toString().length()-1), HashMap.class);
            if(result.containsKey("birthYear")){
                try {
                    return ResponseEntity.ok(deathTableService.getSexDeathTableByBirthAge( Short.valueOf(String.valueOf(result.get("birthYear")))));
                } catch (SQLDataException e) {
                    e.printStackTrace();
                    return new ResponseEntity<String>(e.getMessage(),
                            HttpStatus.NOT_FOUND);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<String>(allParams.values().toString(),
                HttpStatus.OK);


    }


}
