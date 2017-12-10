package hello;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ValidatorTests {
    private String email;
    private String phoneNumber;
    private String number;
    private Boolean expectedResult;

    public ValidatorTests(String email, String phoneNumber, String number, Boolean expectedResult) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.number = number;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][] {
                { "kamil", "123456789", "3", false },
                { "kamil@wp.pl", "12345678", "3", false },
                { "kamil@wp.pl", "123456789", "-1", false },
                { "kamil", "12345678", "3", false },
                { "kamil", "123456789", "-1", false },
                { "kamil@gmail.cp", "+123456789", "-1", false },
                { "kamil", "12345678", "-1", false },
                { "kami314.343@wdsa.xsa", "123456789", "3", true },
                { "kamil0382@wds.ewq.as", "123456789", "3", true }

        });
    }

    @Test
    public void testValidator() throws Exception {
        assertEquals(expectedResult,
                DataValidator.isValidate(email, phoneNumber, number));
    }

}