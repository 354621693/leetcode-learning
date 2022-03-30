
public class Test {
    public static void test1() {
        int i = 5;
        int b = i++;
        i++;
        int a = i;
        a++;
        System.out.println(i + "" + a);
    }

    public static void main(String a[]) {
        test1();
    }

}
