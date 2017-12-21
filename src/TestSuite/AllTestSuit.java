package TestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import test.ContactMessageServiceTest;
import test.CreateAccountServiceTest;
import test.ServiceTest;

@RunWith(Suite.class)
@Suite.SuiteClasses ({
        ServiceTest.class,
        ContactMessageServiceTest.class,
        CreateAccountServiceTest.class
})

public class AllTestSuit {
}
