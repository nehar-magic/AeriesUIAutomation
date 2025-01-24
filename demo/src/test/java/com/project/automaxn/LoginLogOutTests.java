package com.project.automaxn;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class LoginLogOutTests extends BaseTest {

    @Test @Ignore
    public void verifyLogin() {
        
        Login();
    }

    @Test  @Ignore
    public void verifyLogout() {

        Login();
        Logout();
    }
}