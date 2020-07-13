package ProfilePackage;

import java.util.Date;

public class User {
    private int userId;
    private String password;
    private String userName;
    private String name;
    private String surname;
    private Date birthDate;
    private String birthPlace;
    private String status;

    public User(String userName, int userId, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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
}
