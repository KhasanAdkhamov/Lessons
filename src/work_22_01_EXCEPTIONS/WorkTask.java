package work_22_01_EXCEPTIONS;

public class WorkTask {


    public static void main(String[] args) {
        try {

            System.out.println(validatePassword("qwerty"));
        }catch (WeakPasswordEx e){
            System.out.println("Cлабый пароль: " + e.getMessage());
        }
    }


    /*

    ### Задание 1: Валидация пароля (⭐⭐)

    Метод `validatePassword()` выбрасывает `WeakPasswordException`, если пароль меньше 8 символов или не содержит цифр.
     */
    public static String validatePassword(String password) throws WeakPasswordEx {
        if (password == null || password.length() < 8) {
            throw new WeakPasswordEx("Пароль должен иметь минимум 8 символов");
        }
        boolean hasDigit = password.chars().anyMatch(Character::isDigit);
//        for (char ch : password.toCharArray()) {
//            if(Character.isDigit(ch)){
//                hasDigit = true;
//                break;
//            }
//        }
        if(!hasDigit){
            throw new WeakPasswordEx("Пароль должен иметь хотя бы одну цифру");
        }
        return password;
    }
}
