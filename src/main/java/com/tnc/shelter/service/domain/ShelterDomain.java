package com.tnc.shelter.service.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Accessors(chain = true)
public class ShelterDomain {
    private Long id;
    private String name;
    private String city;
//    private List<AnimalDomain> animals = new ArrayList<>();
    private String environment;

}
