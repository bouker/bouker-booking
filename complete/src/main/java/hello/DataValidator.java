package hello;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidator {

    public static boolean isValidate(String email, String phoneNumber, String number){
        return isEmailValidate(email) &&
                isPhoneNumberValidate(phoneNumber) &&
                isNumberValidate(number);
    }

    public static boolean isEmailValidate(String email){
        Pattern emailRegex =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        Matcher matcher = emailRegex .matcher(email);
        return matcher.find();
    }

    public static boolean isPhoneNumberValidate(String phoneNumber){
        Pattern phoneNumberRegex = Pattern.compile("^[0-9]{9}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = phoneNumberRegex .matcher(phoneNumber);
        return matcher.find();
    }

    public static boolean isNumberValidate(String number){
        return (Integer.parseInt(number) > 0);
    }

}
