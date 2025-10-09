package com.ShiftWebService_02.yuuko;

import org.springframework.http.ResponseEntity;
//このクラスをhtmlコントローラーとして認識させる
import org.springframework.stereotype.Controller;
//SporingBootからhtmlに情報を渡すためのもの
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//htmlからSpringBootに情報を渡すため
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.ArrayList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

@Controller
public class ShiftController {
    private final DataRepository repository;
    private int day = 01;
    private int month = 10;
    private int year = 2025;
    private final ArrayList<String> TIME_SLOTS = new ArrayList<>(List.of(
            "06:00", "07:00", "08:00", "09:00", "10:00", "11:00",
            "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00"));

    public ShiftController(DataRepository repository) {
        this.repository = repository;
    }

    // トップページを返すメソッド
    @GetMapping("/")
    public String index(Model model) {
        System.out.println("トップページを表示します！");
        ArrayList<LocalDate> dateList = new ArrayList<>();
        LocalDate startDate = LocalDate.of(this.year, this.month, this.day);
        for (int i = 0; i < 7; i++) {
            LocalDate currentDate = startDate.plusDays(i);
            dateList.add(currentDate);
        }
        model.addAttribute("names", repository.findAll());
        model.addAttribute("sevenDays", dateList);
        model.addAttribute("timeSlots", this.TIME_SLOTS);
        return "index";
    }

    @GetMapping("/shift/manyadd")
    public String manyadd(Model model) {
        System.out.println("manyaddを表示します!");
        ArrayList<LocalDate> dateList = new ArrayList<>();
        LocalDate startDate = LocalDate.of(this.year, this.month, this.day);
        for (int i = 0; i < 7; i++) {
            LocalDate currentDate = startDate.plusDays(i);
            dateList.add(currentDate);
        }
        model.addAttribute("sevenDays", dateList);
        model.addAttribute("timeSlots", this.TIME_SLOTS);
        return "manyadd";
    }

    @PostMapping("/shift/add")
    public String PostName(Model model, @RequestParam String name,
            @RequestParam String shiftDate,
            @RequestParam String shiftTime) {
        System.out.printf("%s%sに名前(%s)を追加しました!\n", shiftDate, shiftTime, name);
        DataController data = new DataController();
        LocalDate datepart = LocalDate.parse(shiftDate);
        LocalTime timePart = LocalTime.parse(shiftTime);
        LocalDateTime shifTime = LocalDateTime.of(datepart, timePart);
        data.setName(name);
        data.setShiftDateTime(shifTime);
        repository.save(data);
        return "redirect:/";
    }

    @GetMapping("/shift/info/{id}")
    public String infoShift(Model model, @PathVariable Long id) {
        System.out.printf("%sの情報を表示します！\n", repository.findById(id).get().getName());
        model.addAttribute("data", repository.findById(id).get());
        return "delete";
    }

    @PostMapping("/shift/info/delete")
    public String delete(Model model, @RequestParam long id) {
        String name = repository.findById(id).get().getName();
        repository.deleteById(id);
        System.out.printf("%sを削除しました！\n", name);
        return "redirect:/";
    }

    @GetMapping("/ping")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("OK");
    }
}
