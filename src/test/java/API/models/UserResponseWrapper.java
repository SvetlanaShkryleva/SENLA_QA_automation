package API.models;

public class UserResponseWrapper {
    private UserResponse data;
    private String requestId;

    public UserResponse getData() { return data; }
    public void setData(UserResponse data) { this.data = data; }

    public String getRequestId() { return requestId; }
    public void setRequestId(String requestId) { this.requestId = requestId; }
}