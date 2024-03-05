package com.example.Demand_Management.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;

@Entity
@Table(name="Demands")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Demand {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String project_name;
    private String mgr_name;
    private String level;
    private String city;
    private DemandStatus status;
    private Integer duration;
    private String startDate;
    private HashMap<String, Integer> skills = new HashMap<>();


}
