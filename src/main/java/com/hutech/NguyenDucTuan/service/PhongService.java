package com.hutech.NguyenDucTuan.service;

import com.hutech.NguyenDucTuan.model.Phong;
import com.hutech.NguyenDucTuan.repository.PhongRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PhongService {
    private final PhongRepository phongRepository;

    public List<Phong> getAllPhongs() {
        return phongRepository.findAll();
    }

    public Optional<Phong> getPhongByMaPhong(String maPhong) {
        return phongRepository.findByMaPhong(maPhong);
    }

    public void addPhong(Phong phong) {
        phongRepository.save(phong);
    }

    public void updatePhong(@NotNull Phong phong) {
        Phong existingPhong = phongRepository.findByMaPhong(phong.getMaPhong())
                .orElseThrow(() -> new IllegalStateException("Phong with MaPhong " + phong.getMaPhong() + " does not exist."));
        existingPhong.setTenPhong(phong.getTenPhong());
        phongRepository.save(existingPhong);
    }

    public void deletePhongByMaPhong(String maPhong) {
        if (!phongRepository.existsByMaPhong(maPhong)) {
            throw new IllegalStateException("Phong with MaPhong " + maPhong + " does not exist.");
        }
        phongRepository.deleteByMaPhong(maPhong);
    }
}
