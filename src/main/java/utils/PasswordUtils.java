package utils;

public class PasswordUtils {
    public String passwordRepeat(String password){
        return password.substring(0, password.length()-1);
    }
}
