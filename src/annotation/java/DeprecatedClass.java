package annotation.java;

public class DeprecatedClass {

    public void call1() {
        System.out.println("DeprecatedClass.call1");
    }

    @Deprecated
    public void call2() {
        System.out.println("DeprecatedClass.call2");
    }

    @Deprecated(forRemoval = true)
    public void call3() {
        System.out.println("DeprecatedClass.call3");
    }
}
