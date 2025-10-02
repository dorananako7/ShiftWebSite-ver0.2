package com.ShiftWebService_02.yuuko;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ShiftWebService_02.yuuko.DataController;

public interface DataRepository extends JpaRepository <DataController, Long> {
}
