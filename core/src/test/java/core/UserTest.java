package core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Assertions;

import core.User;

public class UserTest {
    
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
    }

    private boolean invokePrivateMethod(String methodName, String input) {
        try {
            Method method = User.class.getDeclaredMethod(methodName, String.class);
            method.setAccessible(true);
            return (boolean) method.invoke(user, input);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return false;
        }
    }



    @Test
    public void testValidUsername(){
        String correctUsername = "olanordmann@gmail.com";
        String correctPassword = "ola123//";


    }



}
