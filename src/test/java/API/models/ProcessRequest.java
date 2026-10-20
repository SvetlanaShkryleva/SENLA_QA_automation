package API.models;

import lombok.Data;

@Data
public class ProcessRequest {
    private int applId;
    private int staffid;
    private String action;

}