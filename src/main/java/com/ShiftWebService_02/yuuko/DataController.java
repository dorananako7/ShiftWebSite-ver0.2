package com.ShiftWebService_02.yuuko;

//このクラスがエンティティとすることを明示する
import jakarta.persistence.Entity;
//テーブルの名前を指定する
import jakarta.persistence.Table;
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

    @Column(name = "shiftDate")
    private String shiftDate;

    @Column(name = "shiftTime")
    private String shiftTime;

    public String getName() {
        return this.name;
    }

    public String getShiftDate() {
        return this.shiftDate;
    }

    public String getShiftTime() {
        return this.shiftTime;
    }

    public Long getId() {
        return this.id;
    }

    void setName(String name) {
        this.name = name;
    }

    public void setShiftDate(String date) {
        this.shiftDate = date;
    }

    public void setShiftTime(String time) {
        this.shiftTime = time;
    }

}
