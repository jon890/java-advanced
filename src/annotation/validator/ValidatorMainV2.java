package annotation.validator;

import static util.MyLogger.log;

public class ValidatorMainV2 {

    public static void main(String[] args) {
        User user = new User("user", 0);
        Team team = new Team("teamA", 10000);

        try {
            log("===== user 검증");
            Validator.validate(user);
        } catch (Exception e) {
            log(e);
        }

        try {
            log("===== team 검증");
            Validator.validate(team);
        } catch (Exception e) {
            log(e);
        }
    }
}
