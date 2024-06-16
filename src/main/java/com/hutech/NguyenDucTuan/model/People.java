package com.hutech.NguyenDucTuan.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "peoples")
public class People {
    @Id

    private String maPeople;
    @NotBlank(message = "Tên nhân viên không được để trống")
    private String tenPeople;
    @NotBlank(message = "Phái không được để trống")
    private String phai;
    @NotBlank(message = "Nơi sinh không được để trống")
    private String noiSinh;

    @ManyToOne
    @JoinColumn(name = "phongban_maphong")
    private Phong phong;

    @NotNull(message = "Số lương không được để trống")
    @Min(value = 1, message = "Số lương không được nhỏ hơn 1")
    private int luong;
}
