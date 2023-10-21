package com.warehouse.project.data;

import java.time.LocalDateTime;
import java.util.List;

public class Release extends StoreAction{

    Release(LocalDateTime date, List<ActionCommodity> commodities, Contractor contractor, Long docNumber){
        super(date, commodities, contractor, docNumber);
    }
}
