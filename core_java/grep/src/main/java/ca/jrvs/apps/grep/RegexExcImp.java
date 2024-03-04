package ca.jrvs.apps.grep;

public class RegexExcImp implements RegexExc {
    public static void main(String[] args){
        String testIP1 = "192.16.0.1";
        String testIP2 = "182.168.100.100";

        String testJPEG1 = "documents.jpeg";
        String testJPEG2 = "banana.jpg";

        String testEmpty1 = "";
        String testEmpty2 = ".";

        System.out.println("testIP1's validity is " + matchIp(testIP1));
        System.out.println("testIP2's validity is " + matchIp(testIP2));

        System.out.println("testJPEG1's validity is " + matchJpeg(testJPEG1));
        System.out.println("testJPEG2's validity is " + matchJpeg(testJPEG2));

        System.out.println("testEmpty1's validity is " + isEmptyLine(testEmpty1));
        System.out.println("testEmpty2's validity is " + isEmptyLine(testEmpty2));


    }

    public static boolean matchIp(String ip){
        String regex = "^[0-9]{1,3}[.]{1}[0-9]{1,3}[.]{1}[0-9]{1,3}[.]{1}[0-9]{1,3}$";
        return ip.matches(regex);
    }

    public static boolean isEmptyLine(String line) {
        String regex = "^\\s*$";
        return line.matches(regex);
    }

    public static boolean matchJpeg(String filename){
        String regex = "^[a-z][a-z]*[.](jpg|jpeg)$";
        return filename.matches(regex);
    }
}
