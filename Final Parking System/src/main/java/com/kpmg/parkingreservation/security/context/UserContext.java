package com.kpmg.parkingreservation.security.context;


import org.springframework.stereotype.Component;

@Component
public class UserContext {
    private static final ThreadLocal<Integer> empId = new ThreadLocal<>();
    private static final ThreadLocal<String> empType = new ThreadLocal<>();
    private static final ThreadLocal<String> username = new ThreadLocal<>();

    public void setEmpId(Integer id) { empId.set(id); }
    public Integer getEmpId() { return empId.get(); }
    public void setEmpType(String type) { empType.set(type); }
    public String getEmpType() { return empType.get(); }
    public void setUsername(String name) { username.set(name); }
    public String getUsername() { return username.get(); }
    public void clear() {
        empId.remove();
        empType.remove();
        username.remove();
    }
}