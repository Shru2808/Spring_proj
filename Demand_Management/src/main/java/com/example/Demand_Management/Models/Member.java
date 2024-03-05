package com.example.Demand_Management.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;

@Entity
@Table(name="Members")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer eid;
    private String first_name;
    private String last_name;
    private String doj;
    private String location;
    private Integer overall_exp;
    private MemberStatus status;
    private HashMap<String, Integer> member_skills = new HashMap<>();

}
