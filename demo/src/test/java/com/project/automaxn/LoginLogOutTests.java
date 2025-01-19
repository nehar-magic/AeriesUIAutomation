package com.project.automaxn;

import org.testng.annotations.Test;

public class LoginLogOutTests extends BaseTest {

    @Test
    public void verifyLogin() {
        
        Login();
    }

    @Test
    public void verifyLogout() {

        Login();
        Logout();
    }
}