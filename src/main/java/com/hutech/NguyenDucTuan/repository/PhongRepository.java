package com.hutech.NguyenDucTuan.repository;

import com.hutech.NguyenDucTuan.model.Phong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhongRepository extends JpaRepository<Phong, String> {

    Optional<Phong> findByMaPhong(String maPhong);

    boolean existsByMaPhong(String maPhong);

    void deleteByMaPhong(String maPhong);
}
