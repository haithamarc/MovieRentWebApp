package entity.json;

import javax.validation.constraints.NotNull;

public class StaffJson {
    private boolean active;
    private String email;
    @NotNull
    private String firstName;
    private int id;
    @NotNull
    private String lastName;
    @NotNull
    private String password;
    private String picture;
    @NotNull
    private String username;

    public StaffJson() {
    }

    public StaffJson(boolean active, String email, String firstName, int id, String lastName, String password, String picture, String username) {
        this.active = active;
        this.email = email;
        this.firstName = firstName;
        this.id = id;
        this.lastName = lastName;
        this.password = password;
        this.picture = picture;
        this.username = username;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
