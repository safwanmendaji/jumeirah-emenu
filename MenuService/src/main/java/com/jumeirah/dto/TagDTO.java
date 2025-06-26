package com.jumeirah.dto;

import lombok.Data;

@Data
public class TagDTO {

    private String tagId;
    private String title;
    private boolean display;
    private String code;
    private boolean filter;
    private IconDTO icon;
    private String type;
}