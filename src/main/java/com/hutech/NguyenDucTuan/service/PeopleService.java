package com.hutech.NguyenDucTuan.service;

import com.hutech.NguyenDucTuan.model.People;
import com.hutech.NguyenDucTuan.repository.PeopleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PeopleService {
    private final PeopleRepository peopleRepository;

    public Page<People> getAllPeoples(Pageable pageable) {
        return peopleRepository.findAll(pageable);
    }

    public Optional<People> getPeopleByMaPeople(String maPeople) {
        return peopleRepository.findById(maPeople);
    }

    public void addPeople(People people) {
        peopleRepository.save(people);
    }

    public void updatePeople(People people) {
        People existingPeople = peopleRepository.findById(people.getMaPeople())
                .orElseThrow(() -> new IllegalStateException("People with MaPeople " + people.getMaPeople() + " does not exist."));
        existingPeople.setTenPeople(people.getTenPeople());
        existingPeople.setPhai(people.getPhai());
        existingPeople.setNoiSinh(people.getNoiSinh());
        existingPeople.setPhong(people.getPhong());
        existingPeople.setLuong(people.getLuong());
        peopleRepository.save(existingPeople);
    }

    public void deletePeopleByMaPeople(String maPeople) {
        if (!peopleRepository.existsById(maPeople)) {
            throw new IllegalStateException("People with MaPeople " + maPeople + " does not exist.");
        }
        peopleRepository.deleteById(maPeople);
    }
}
