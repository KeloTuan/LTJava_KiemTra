package com.hutech.NguyenDucTuan.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "phongbans")
public class Phong {
    @Id

    private String maPhong;
    @NotBlank(message = "Tên phòng là bắt buộc")
    private String tenPhong;
}
