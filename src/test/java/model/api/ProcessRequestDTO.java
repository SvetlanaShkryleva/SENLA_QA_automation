package model.api;

import lombok.Data;

@Data
public class ProcessRequestDTO {
    private int applId;
    private int staffid;
    private String action;

}