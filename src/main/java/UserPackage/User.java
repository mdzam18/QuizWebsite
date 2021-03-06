package UserPackage;

import java.util.Date;
import java.util.Objects;

public class User {
    private int userId;
    private String password;
    private String userName;
    private String name;
    private String surname;
    private String birthPlace;
    private String status;
    private boolean isAdministrator;

    public User(String userName, int userId, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        isAdministrator = false;
    }

    public boolean isAdministrator() {
        return isAdministrator;
    }

    public void setAdministrator(boolean administrator) {
        isAdministrator = administrator;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    @Override
    public boolean equals(Object user) {
        if (this == user) return true;
        if (user == null || getClass() != user.getClass()) return false;
        return userName.equals(((User) user).getUserName()) &&
                userId == ((User) user).getUserId() && password.equals(((User) user).getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, userId, password);
    }
}
