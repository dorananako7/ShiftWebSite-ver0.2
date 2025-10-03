package com.ShiftWebService_02.yuuko;

//このクラスがエンティティとすることを明示する
import jakarta.persistence.Entity;
//テーブルの名前を指定する
import jakarta.persistence.Table;

import java.time.LocalDateTime;

//カラムの名前を指定できる
import jakarta.persistence.Column;
//主キーであることを明示する
import jakarta.persistence.Id;
//主キーを自動で明記する
import jakarta.persistence.GeneratedValue;
//主キーの採番方法を指定する
import jakarta.persistence.GenerationType;

@Table(name = "shifsDB")
@Entity
public class DataController {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "userName")
    private String name;

    @Column(name="date")
    private LocalDateTime shiftDateTime;

    
   

    public String getName() {
        return this.name;
    }

    public Long getId() {
        return this.id;
    }

    public LocalDateTime getShiftDateTime() {
        return this.shiftDateTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShiftDateTime(LocalDateTime shifDateTime) {
        this.shiftDateTime = shifDateTime;
    }

}
