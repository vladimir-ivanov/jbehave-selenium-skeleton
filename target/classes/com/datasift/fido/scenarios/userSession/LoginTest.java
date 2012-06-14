package com.datasift.fido.scenarios.userSession;

import com.datasift.fido.stepdefinitions.*;

public class LoginTest extends JBehaveStory {

    public LoginTest() {
        useSteps(
                new BeforeAndAfterSteps(),
                new UserSessionSteps()
        );
    }
}
