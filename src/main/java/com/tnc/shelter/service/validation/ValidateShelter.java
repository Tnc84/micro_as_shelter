package com.tnc.shelter.service.validation;

import com.tnc.shelter.service.domain.ShelterDomain;
import com.tnc.shelter.service.exception.ShelterAddressException;
import com.tnc.shelter.service.exception.ShelterNameException;
import com.tnc.shelter.service.impl.ShelterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Locale;

public class ValidateShelter {

    @Autowired
    static ShelterServiceImpl shelterServiceImpl;

    public static void validateShelter(ShelterDomain shelterDomain, String name) throws ShelterAddressException, ShelterNameException {
        var findShelterByName = shelterServiceImpl.findByName(name);
        if (!shelterDomain.getCity().toLowerCase(Locale.ROOT).contains("iasi")) {
            throw new ShelterAddressException("The shelter is not from Iasi");
        }
    }
}
