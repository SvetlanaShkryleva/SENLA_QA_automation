package model.api;

public class UserResponseWrapperDTO {
    private UserResponseDTO data;
    private String requestId;

    public UserResponseDTO getData() { return data; }
    public void setData(UserResponseDTO data) { this.data = data; }

    public String getRequestId() { return requestId; }
    public void setRequestId(String requestId) { this.requestId = requestId; }
}