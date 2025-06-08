package annotation.mapping;

import java.lang.reflect.Method;

public class TestControllerMain {

    public static void main(String[] args) {
        TestController testController = new TestController();

        Class<? extends TestController> aClass = testController.getClass();
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {//
            SimpleMapping simpleMapping = declaredMethod.getAnnotation(SimpleMapping.class);
            if (simpleMapping != null) {
                System.out.println("[" + simpleMapping.value() + "] -> " + declaredMethod);
            }
        }

    }
}
