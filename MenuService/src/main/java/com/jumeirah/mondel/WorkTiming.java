package com.jumeirah.mondel;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "work_timings")
@Data
public class WorkTiming {
    @Id
    private UUID workTimingId =UUID.randomUUID();
    private String menuId;
    private String openTime;
    private boolean openForOrder;
    private String instructionMsg;
    private String closeTime;


}