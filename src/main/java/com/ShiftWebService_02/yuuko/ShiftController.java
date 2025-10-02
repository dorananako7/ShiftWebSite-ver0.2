package com.ShiftWebService_02.yuuko;

//このクラスをhtmlコントローラーとして認識させる
import org.springframework.stereotype.Controller;
//SporingBootからhtmlに情報を渡すためのもの
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//htmlからSpringBootに情報を渡すため
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ShiftController {
    private final DataRepository repository;

    public ShiftController(DataRepository repository) {
        this.repository = repository;
    }

    // トップページを返すメソッド
    @GetMapping("/")
    public String index(Model model) {
        System.out.println("トップページを表示します！");
        model.addAttribute("names", repository.findAll());
        return "index";
    }

    @PostMapping("/shift/add")
    public String PostName(Model model, @RequestParam String name, @RequestParam String shiftDate, @RequestParam String shiftTime) {
        System.out.printf("%s%sに名前(%s)を追加しました!\n",shiftDate, shiftTime, name);
        DataController data = new DataController();
        data.setName(name);
        data.setShiftDate(shiftDate);
        data.setShiftTime(shiftTime);
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
        System.out.printf("%sを削除しました！\n",name);
        return "redirect:/";
    }
}
