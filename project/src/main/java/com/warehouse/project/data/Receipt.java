package com.warehouse.project.data;

import java.time.LocalDateTime;
import java.util.List;

public class Receipt extends StoreAction{

    Receipt(LocalDateTime date, List<ActionCommodity> commodities, Contractor contractor, Long docNumber){
        super(date, commodities, contractor, docNumber);
    }
}
