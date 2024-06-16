package com.hutech.NguyenDucTuan.repository;

import com.hutech.NguyenDucTuan.model.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<People, String>, PagingAndSortingRepository<People, String> {
    Optional<People> findByMaPeople(String maPeople);
    boolean existsByMaPeople(String maPeople);
    void deleteByMaPeople(String maPeople);
}
