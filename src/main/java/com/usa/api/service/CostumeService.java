package com.usa.api.service;

import com.usa.api.model.Costume;
import com.usa.api.repository.CostumeRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CostumeService {

    @Autowired
    private CostumeRepository costumeRepository;

    public List<Costume> getAll() {
        return costumeRepository.getAll();
    }

    public Optional<Costume> getCostume(int id) {
        return costumeRepository.getCostume(id);
    }

    public Costume save(Costume costume) {
        if (costume.getId() == null) {
            return costumeRepository.save(costume);
        } else {
            Optional<Costume> costumeAux = costumeRepository.getCostume(costume.getId());
            if (costumeAux.isEmpty()) {
                return costumeRepository.save(costume);
            } else {
                return costume;
            }
        }
    }

    public Costume update(Costume costume) {
        if (costume.getId() != null) {
            Optional<Costume> costumeAux = costumeRepository.getCostume(costume.getId());
            if (!costumeAux.isEmpty()) {
                if (costume.getBrand()!= null) {
                    costumeAux.get().setBrand(costume.getBrand());
                }
                if (costume.getYear() != null) {
                    costumeAux.get().setYear(costume.getYear());
                }
                if (costume.getName() != null) {
                    costumeAux.get().setName(costume.getName());
                }
                if (costume.getDescription() != null) {
                    costumeAux.get().setDescription(costume.getDescription());
                }
                return costumeRepository.save(costumeAux.get());
            }
        }
        return costume;
    }

    public boolean delete(int id) {
        Optional<Costume> costumeAux = costumeRepository.getCostume(id);
        if (!costumeAux.isEmpty()) {
            costumeRepository.delete(costumeAux.get());
            return true;
        }
        return false;
    }
}
