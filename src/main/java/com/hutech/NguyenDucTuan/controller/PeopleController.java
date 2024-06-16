package com.hutech.NguyenDucTuan.controller;

import com.hutech.NguyenDucTuan.model.People;
import com.hutech.NguyenDucTuan.service.PeopleService;
import com.hutech.NguyenDucTuan.service.PhongService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/peoples")
public class PeopleController {
    @Value("${static.images.directory}")
    private String imagesDirectory;
    private final PeopleService peopleService;
    private final PhongService phongService;

    @GetMapping
    public String getAllPeoples(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "4") int size,
                                Model model) {
        Page<People> peoplePage = peopleService.getAllPeoples(PageRequest.of(page, size));
        model.addAttribute("peoples", peoplePage.getContent());
        model.addAttribute("totalPages", peoplePage.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("phongs", phongService.getAllPhongs());
        return "/peoples/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("people", new People());
        model.addAttribute("phongs", phongService.getAllPhongs());
        return "/peoples/add";
    }

    @PostMapping("/add")
    public String addPeople(@Valid People people, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("phongs", phongService.getAllPhongs());
            return "/peoples/add";
        }
        peopleService.addPeople(people);
        return "redirect:/peoples";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable String id, Model model) {
        People people = peopleService.getPeopleByMaPeople(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid People Id:" + id));
        model.addAttribute("people", people);
        model.addAttribute("phongs", phongService.getAllPhongs());
        return "/peoples/update";
    }

    @PostMapping("/edit/{id}")
    public String updatePeople(@PathVariable String id, @Valid People people, BindingResult result, Model model) {
        if (result.hasErrors()) {
            people.setMaPeople(id);
            model.addAttribute("phongs", phongService.getAllPhongs());
            return "/peoples/update";
        }
        peopleService.updatePeople(people);
        return "redirect:/peoples";
    }

    @GetMapping("/delete/{id}")
    public String deletePeople(@PathVariable("id") String id) {
        People people = peopleService.getPeopleByMaPeople(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid People Id:" + id));
        peopleService.deletePeopleByMaPeople(id);
        return "redirect:/peoples";
    }
}
