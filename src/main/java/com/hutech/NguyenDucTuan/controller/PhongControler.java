package com.hutech.NguyenDucTuan.controller;

import com.hutech.NguyenDucTuan.model.Phong;
import com.hutech.NguyenDucTuan.service.PhongService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/phongs")
public class PhongControler {
    private final PhongService phongService;

    @GetMapping
    public String getAllPhongs(Model model) {
        List<Phong> phongs = phongService.getAllPhongs();
        model.addAttribute("phongs", phongs);
        return "/phongs/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("phong", new Phong());
        return "/phongs/add";
    }

    @PostMapping("/add")
    public String addPhong(@Valid Phong phong, BindingResult result) {
        if (result.hasErrors()) {
            return "/phongs/add";
        }
        phongService.addPhong(phong);
        return "redirect:/phongs";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable String id, Model model) {
        Phong phong = phongService.getPhongByMaPhong(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category Id:" + id));
        model.addAttribute("phong", phong);
        return "/phongs/update";
    }

    @PostMapping("/edit/{id}")
    public String updatePhong(@PathVariable String id, @Valid Phong phong, BindingResult result, Model model) {
        if (result.hasErrors()) {
            phong.setMaPhong(id);
            return "/phongs/update";
        }
        phongService.updatePhong(phong);
        model.addAttribute("phongs", phongService.getAllPhongs());
        return "redirect:/phongs";
    }

    @GetMapping("/delete/{id}")
    public String deletePhong(@PathVariable("id") String id, Model model) {
        Phong phong = phongService.getPhongByMaPhong(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid phong maPhong:" + id));
        phongService.deletePhongByMaPhong(id);
        model.addAttribute("phongs", phongService.getAllPhongs());
        return "redirect:/phongs";
    }
}
