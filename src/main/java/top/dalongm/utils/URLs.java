package top.dalongm.utils;

import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class URLs {

    public static void main(String[] args) {
        System.out.println(getRandom(5));
        System.out.println(valid("www.baidu.com"));

        System.out.println(valid("ww.baidu.com"));
        System.out.println(valid("htT:www.baidu.com"));
    }

    public static String getRandom(){
        return getRandom(5);
    }

    public static String getRandom(int length) {
        StringBuilder sb = new StringBuilder();
        Random rnd = new Random();
        int r;
        for (int i = 0; i < length; i++) {
            r = (int) (rnd.nextDouble() * 62.0);
            sb.append(getChar(r));
        }
        return sb.toString();
    }

    public static String getRandom(List<Character> charlist, int length) {
        StringBuilder sb = new StringBuilder();
        Random rnd = new Random();
        int r;

        for (int i = 0; i < length; i++) {
            r = (int) (rnd.nextDouble() * (double) charlist.size());
            sb.append(charlist.get(r));
        }
        return sb.toString();
    }

    private static char getChar(int n) {
        if (n < 10) {
            return (char) (n+'0');
        } else if (n < 36) {
            return (char) ('A' + n - 10);
        } else if (n < 62) {
            return (char) ('a' + n - 36);
        }
        return '0';
    }

    public static boolean valid(String url){
        if(url==null||url.trim().equals("")){
            return false;
        }
        String pattern = "^((https|http|ftp|rtsp|mms)?://)"
                + "?(([0-9a-zA-Z_!~*'().&=+$%-]+: )?[0-9a-zA-Z_!~*'().&=+$%-]+@)?" //ftp的user
                + "(([0-9]{1,3}\\.){3}[0-9]{1,3}" // IP形式的URL 199.194.52.184
                + "|" // 允许IP和DOMAIN（域名）
                + "([0-9a-zA-Z_!~*'()-]+\\.)*" // 域名- www.
                + "([0-9a-zA-Z][0-9a-z-]{0,61})?[0-9a-zA-Z]\\." // 二级域名
                + "[a-zA-Z]{2,6})" // 一级域名
                + "(:[0-9]{1,4})?" // 端口 80
                + "((/?)|"
                + "(/[0-9a-zA-Z_!~*'().;?:@&=+$,%#-]+)+/?)$";

        return Pattern.matches(pattern,url);
    }
}
