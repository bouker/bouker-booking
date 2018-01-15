package hello;


import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidator {
    static Logger logger = Logger.getLogger(DataValidator.class.getName());

    public static boolean isValidate(String email, String phoneNumber, String number){
        logger.info("Validate data - (mail: " + email +", phoneNumber: " +
                phoneNumber + ", number: " + number + ")");
        return isEmailValidate(email) &&
                isPhoneNumberValidate(phoneNumber) &&
                isNumberValidate(number);
    }

    public static boolean isEmailValidate(String email){
        String regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        return ValidateByRegex(regex, email, "Email");
    }

    public static boolean isPhoneNumberValidate(String phoneNumber){
        String regex = "^[0-9]{9}$";
        return ValidateByRegex(regex, phoneNumber, "Phone number");
    }

    public static boolean isNumberValidate(String number){
        boolean isValidate = (Integer.parseInt(number) > 0);
        Log(isValidate, "Number");
        return isValidate;
    }

    private static boolean ValidateByRegex(String patternRegex, String data, String objectName){
        Pattern dataRegex = Pattern.compile(patternRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = dataRegex .matcher(data);

        boolean isValidate = matcher.find();
        Log(isValidate, objectName);
        return isValidate;
    }

    private static void Log(boolean isValidate, String objectName){
        if(isValidate){
            logger.info(objectName + " is valid!");
        } else {
            logger.error(objectName + " is NOT valid!");
        }
    }

}
