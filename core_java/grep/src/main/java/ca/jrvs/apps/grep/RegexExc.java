package ca.jrvs.apps.grep;

public interface RegexExc{
        /**
         * Return true if filename extension is jpg or jpeg (case is checked)
         * @param filename
         * @return
         */
        public static void matchJpeg(String filename){

        };

        /**
         * return true if ip is valid
         * to simplify the problem. IP range is from 0.0.0.0 to 999.999.999.999
         * @param ip
         * @return
         */
        public static void matchIp(String ip){

        };
        /**
         * return true if the line is empty (e.g. empty, whitespace, tabs, etc...)
         * @param line
         * @return
         */
        public static void isEmptyLine(String line){

        };

}
