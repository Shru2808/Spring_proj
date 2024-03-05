package com.example.Demand_Management.controller;

import com.example.Demand_Management.Models.Demand;
import com.example.Demand_Management.repo.Demand_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DemandController {
    @Autowired
    Demand_repo DemandRepository;

    @GetMapping("/getAllDemands")
    public ResponseEntity<List<Demand>> getAllBooks() {
        try {
            List<Demand> DemandList = new ArrayList<>();
            DemandRepository.findAll().forEach(DemandList::add);

            if (DemandList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(DemandList, HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getDemandById/{id}")
    public ResponseEntity<Demand> getBookById(@PathVariable Long id) {
        Optional<Demand> demandObj = DemandRepository.findById(id);
        if (demandObj.isPresent()) {
            return new ResponseEntity<>(demandObj.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addDemand")
    public ResponseEntity<Demand> addBook(@RequestBody Demand demand) {
        try {
            Demand demandObj = DemandRepository.save(demand);
            return new ResponseEntity<>(demandObj, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updateDemands/{id}")
    public ResponseEntity<Demand> updateDemand(@PathVariable Long id, @RequestBody Demand demand) {
        try {
            Optional<Demand> demandData = DemandRepository.findById(id);
            if (demandData.isPresent()) {
                Demand updatedDemandData = demandData.get();
                updatedDemandData.setProject_name(demand.getProject_name());
                updatedDemandData.setMgr_name(demand.getMgr_name());

                Demand demandObj = DemandRepository.save(updatedDemandData);
                return new ResponseEntity<>(demandObj, HttpStatus.CREATED);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteDemandById/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable Long id) {
        try {
            DemandRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deleteAllDemands")
    public ResponseEntity<HttpStatus> deleteAllBooks() {
        try {
            DemandRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
