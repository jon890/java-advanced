package reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class MethodV3 {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("호출 메서드 : ");
        String methodName = scanner.nextLine();

        System.out.println("숫자 1 : ");
        int a = scanner.nextInt();
        System.out.println("숫자 2 : ");
        int b = scanner.nextInt();

        Calculator calculator = new Calculator();

        Class<? extends Calculator> aClass = calculator.getClass();
        Method method = aClass.getMethod(methodName, int.class, int.class);

        Object returnValue = method.invoke(calculator, a, b);
        System.out.println("returnValue : " + returnValue);
    }
}
