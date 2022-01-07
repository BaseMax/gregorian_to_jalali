

public class GregorianToJalaliTest {

    public static void main(String[] args) {
        System.out.println(GregorianToJalali.gregorianToJalaliStr(2022,1,7).equals("1400/10/17"));
        System.out.println(GregorianToJalali.gregorianToJalaliStr(2022,3,20).equals("1400/12/29"));
    }
}
