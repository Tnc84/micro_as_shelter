package com.tnc.shelter.controller;

import com.tnc.shelter.controller.dto.AnimalDTO;
import com.tnc.shelter.controller.dto.ShelterDTO;
import com.tnc.shelter.controller.mapper.ShelterDTOMapper;
import com.tnc.shelter.service.exception.ShelterAddressException;
import com.tnc.shelter.service.exception.ShelterNameException;
import com.tnc.shelter.service.impl.FeignAnimalProxy;
import com.tnc.shelter.service.interfaces.ShelterService;
import com.tnc.shelter.service.validation.OnCreate;
import com.tnc.shelter.service.validation.OnUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "/shelters")
@RestController
@RequiredArgsConstructor
@Validated
//@PreAuthorize("isAuthenticated() && hasRole('MOD')")
public class ShelterController {

    private final FeignAnimalProxy feignAnimalProxy;
    private final ShelterService shelterService;
    private final ShelterDTOMapper shelterDTOMapper;

    @GetMapping("/getAllAnimals")
    public List<AnimalDTO> getAllAnimalsFeign() {
        return feignAnimalProxy.getAllAnimals();
    }

    @GetMapping("/getAnimalByIdFeign/{id}")
    public FeignAnimalProxy getAnimalById() {
        return feignAnimalProxy;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ShelterDTO>> getAll() {
        return ResponseEntity.ok(shelterDTOMapper.toDTOList(shelterService.getAll()));
    }
    @GetMapping("/iasi")
    public ResponseEntity<ShelterDTO> getShelterByName() {
        return ResponseEntity.ok(shelterDTOMapper.toDTO(shelterService.getShelterByName()));
    }

    @PostMapping("/add")
    @Validated(OnCreate.class)
    public ResponseEntity<ShelterDTO> add(@Valid @RequestBody ShelterDTO shelterDTO) throws ShelterAddressException, ShelterNameException {
        return ResponseEntity.ok(shelterDTOMapper.toDTO(shelterService.add(shelterDTOMapper.toDomain(shelterDTO))));
    }

    @PutMapping("/update")
    @Validated(OnUpdate.class)
    public ResponseEntity<ShelterDTO> update(@Valid @RequestBody ShelterDTO shelterDTO) throws ShelterAddressException, ShelterNameException {
        return ResponseEntity.ok(shelterDTOMapper.toDTO(shelterService.add(shelterDTOMapper.toDomain(shelterDTO))));
    }

//    @GetMapping(value = "/{id}")
//    public ResponseEntity<ShelterDTO> get(@PathVariable Long id) {
//        return ResponseEntity.ok(shelterDTOMapper.toDTO(shelterService.get(id)));
//    }

}
