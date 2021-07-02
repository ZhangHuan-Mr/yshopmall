package co.yixiang.modules.quartz.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p> String工具类 </p>
 *
 * @author zhanghuan <br />
 * @version v1.0
 * @date 2020/12/1 14:16 <br />
 * 修改履历 <br />
 * 日期 : 姓名: 修改内容<br />
 */
public class StringUtil {

    public static final String AMP = "&amp;";
    public static final Pattern NUMBER_PATTERN = Pattern.compile("([0-9]+)$");

    private static char[] alpheBic = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static char[] getAlpheBic() {
        return alpheBic;
    }

    private static char[] alphebicAndNumberUpper = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            // 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
            // 'r', 's',
            // 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

    public static char[] getAlphebicAndNumberUpper() {
        return alphebicAndNumberUpper;
    }

    private static char[] alphebicAndNumberLower = new char[]{
            // 'A', 'B', 'C', 'D', 'E', 'F', 'G',
            // 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            // 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

    public static char[] getAlphebicAndNumberLower() {
        return alphebicAndNumberLower;
    }

    private static char[] alphebicAndNumber = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
            'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

    public static char[] getAlphebicAndNumber() {
        return alphebicAndNumber;
    }

    private static char[] number = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

    public static char[] getNumber() {
        return number;
    }

    private static char[] alphebicAndNumberUpperSep = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static char[] getAlphebicAndNumberUpperSep() {
        return alphebicAndNumberUpperSep;
    }

    public static String getAlphaOrder(int order) {
        StringBuilder sb = new StringBuilder();
        int size = alpheBic.length;
        int i = order - 1;
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        while (i >= size) {
            int j = i % size;
            i = i / size - 1;
            sb.insert(0, alpheBic[j]);
        }
        sb.insert(0, alpheBic[i]);
        return sb.toString();
    }

    public static String upcaseFirstLetter(String str) {
        if (str == null) {
            return null;
        }
        String firstChar = str.substring(0, 1);
        return firstChar.toUpperCase() + str.substring(1, str.length());
    }

    public static String[] getFormatedIncreasedString(String firstString, int stepNum, int count) {
        if (firstString == null) {
            return null;
        }

        Matcher m = NUMBER_PATTERN.matcher(firstString);

        if (m.find()) {

            int dIndex = m.start();
            String prefix = firstString.substring(0, dIndex);
            String suffix = firstString.substring(dIndex);
            /*
             * int index = suffix.length(); if (suffix.endsWith("0")) { index = suffix.length() - 1;
             * if (suffix.lastIndexOf("0", index) > 0) { prefix += suffix.substring(0,
             * suffix.lastIndexOf("0", index)); suffix = suffix.substring(suffix.lastIndexOf("0",
             * index)); } } else { prefix += suffix.substring(0, suffix.lastIndexOf("0", index) +
             * 1); suffix = suffix.substring(suffix.lastIndexOf("0", index) + 1); }
             */

            int suffixLenth = suffix.length();

            long in = Long.parseLong(suffix);
            String[] value = new String[count];
            value[0] = firstString;
            for (int i = 1; i < count; i++) {
                long lastNum = in + stepNum * i;
                String last = Long.toString(lastNum);
                if (last.length() < suffixLenth) {
                    last = formatLong(lastNum, suffixLenth);
                }
                value[i] = prefix + last;

            }
            return value;
        }
        return null;
    }

    public static String formatLong(long num, int length) {
        StringBuilder number = new StringBuilder(Long.toString(num));
        while (number.length() < length) {
            number.append("0").append(number);
        }
        return number.toString();
    }

    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat dateTimeFormat = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
        dateTimeFormat.applyPattern(pattern);
        return dateTimeFormat.format(date);
    }

    public static String getRandomUpperStr(int strLength) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strLength; i++) {
            int j = (int) (Math.random() * alphebicAndNumberUpper.length);
            sb.append(alphebicAndNumberUpper[j]);
        }
        return sb.toString();
    }

    public static String getRandomUpperSepStr(int strLength) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strLength; i++) {
            int j = (int) (Math.random() * alphebicAndNumberUpperSep.length);
            sb.append(alphebicAndNumberUpperSep[j]);
        }
        return sb.toString();
    }

    public static String getRandomLowerStr(int strLength) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strLength; i++) {
            int j = (int) (Math.random() * alphebicAndNumberLower.length);
            sb.append(alphebicAndNumberLower[j]);
        }
        return sb.toString();
    }

    public static String getRandomStrAll(int strLength) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strLength; i++) {
            int j = (int) (Math.random() * alphebicAndNumber.length);
            sb.append(alphebicAndNumber[j]);
        }
        return sb.toString();
    }

    public static String getRandomStrNum(int strLength) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strLength; i++) {
            int j = (int) (Math.random() * number.length);
            sb.append(number[j]);
        }
        return sb.toString();
    }


    public static String formatString(String str, int length) {

        while (str.length() < length) {
            str = " " + str;
        }
        return str;
    }

    /**
     * 获得两个日期字符串相差的天数
     *
     * @param time1
     * @param time2
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static long getDateDiffByDay(String time1, String time2, String pattern) throws ParseException {
        long diff = 0;
        SimpleDateFormat ft = new SimpleDateFormat(pattern);
        Date date1 = ft.parse(time1);
        Date date2 = ft.parse(time2);
        diff = date1.getTime() - date2.getTime();
        diff = diff / 1000 / 60 / 60 / 24;
        return diff;
    }

    public static String toStr(String html) {
        String s1, s2;
        s1 = html.replaceAll("<br>", "\n").replaceAll("&39;", "'").replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll(AMP, "&").replaceAll("&34;", "\"").replace("&92;", "\\");
        s2 = s1.replaceAll("<br>", "\n").replaceAll("&39;", "'").replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll(AMP, "&").replaceAll("&34;", "\"").replace("&92;", "\\");

        return s2;

    }

    public static String toHtml(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int len = str.length();
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            switch (c) {
                case '\n':
                    sb.append("<br>");
                    break;
                case '\r':
                    break;
                case '\'':
                    sb.append("&39;");
                    break;
                case '<':
                    sb.append("&lt;");
                    break;
                case '>':
                    sb.append("&gt;");
                    break;
                case '&':
                    sb.append(AMP);
                    break;
                case '"':
                    sb.append("&34;");
                    break;
                case '\\':
                    sb.append("&92;");
                    break;
                default:
                    sb.append(c);
            }
        }
        return sb.toString();
    }

    public static Boolean isEmpty(String org) {
        Boolean flag = Boolean.FALSE;
        if (org == null || "".equals(org.trim())) {
            flag = Boolean.TRUE;
        }
        return flag;
    }


    public static String[] splitStr(String str, String splitStr) {
        String[] splitArray = null;
        String[] subScript = splitStr.split(",");
        if (splitStr.indexOf(",") > -1) {
            splitArray = new String[subScript.length];
            for (int i = 0; i < subScript.length; i++) {
                if (i == 0) {
                    splitArray[i] = str.substring(0, Integer.parseInt(subScript[i]) - 1).trim();
                } else {
                    splitArray[i] = str.substring(Integer.parseInt(subScript[i - 1]) - 1, Integer.parseInt(subScript[i])).trim();
                }
            }
        } else {
            splitArray = str.split(splitStr);
        }
        return splitArray;
    }

    public static String appendStr(String str, String appStr, int totalLength) {
        StringBuilder sbu = new StringBuilder(str);
        int length = totalLength - sbu.length();
        if (length < 1) {
            return sbu.toString();
        }
        for (int i = 0; i < length; i++) {
            sbu = sbu.append(appStr);
        }
        return sbu.toString();
    }

    public static String formatIntToChinaBigNumStr(int num) {
        if (num < 0) num = 0;
        String[] bigLetter = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        String si = num + "";
        char[] cc = new char[5];
        if (si.length() < 5) {
            StringBuilder t = new StringBuilder(si);
            for (int n = 0; n < 5 - si.length(); n++) {
                t = new StringBuilder("0").append(t);
            }
            cc = t.toString().toCharArray();
        } else if (si.length() == 5) {
            cc = si.toCharArray();
        } else {
            return "";
        }
        StringBuilder tt = new StringBuilder();
        for (int j = 0; j < cc.length; j++) {
            tt.append(bigLetter[cc[j] - 48] + "   ");
        }
        return tt.toString();
    }

    public static String appendStr(Integer position, String str, String appStr, int totalLength) {
        StringBuilder sbu = new StringBuilder(str);
        int length = totalLength - sbu.length();
        if (length < 1) {
            return sbu.toString();
        }
        if (-1 == position) {
            for (int i = 0; i < length; i++) {
                sbu = sbu.insert(0, appStr);
            }
        } else {
            for (int i = 0; i < length; i++) {
                sbu = sbu.append(appStr);
            }
        }

        return sbu.toString();
    }

    public static String ReEmptyStr(String s) {
        if (StringUtil.isEmpty(s)) {
            return "";
        } else {
            return s;
        }
    }

    /**
     * 获取在 字符串 中 中文的数量
     *
     * @param str
     * @return
     */
    public static int getChineseNumber(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int result = 0;
        for (char oneChar : str.toCharArray()) {
            Character.UnicodeBlock ub = Character.UnicodeBlock.of(oneChar);
            if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                    || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                    || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
                result++;
            }
        }
        return result;
    }

    /**
     * 正则表达式查询匹配字符串
     *
     * @param str
     * @param pattern
     * @return
     */
    public static String findFirstStringWithRegex(String str, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        if (m.find()) {
            return m.group();
        }
        return null;
    }

    /**
     * 判断string数组中的每一项是否为空
     */
    public static boolean isContainsEmpty(String[] array) {
        for (String element : array) {
            if (StringUtils.isBlank(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 截取字符串长度（半角长度）
     *
     * @param text
     * @param dbcCaseLength
     * @return
     */
    public static String truncateByDbcCase(String text, int dbcCaseLength) {
        int textDbcCaseLength = getLength(text);//半角长度

        /**1.当text的半角长度 <= 截取的半角长度时，直接返回text*/
        if (textDbcCaseLength <= dbcCaseLength) {
            return text;
        }

        /** 当text的半角长度 > 截取的半角长度时, 且text的全角长度=半角长度，则表示内容中不包含全角 **/
        int textSbcCaseLength = text.length(); //全角长度
        if (textSbcCaseLength == textDbcCaseLength) {
            //text都是半角字符
            return text.substring(0, dbcCaseLength);
        }

        //存在半角的情况
        int minLength = dbcCaseLength / 2;
        StringBuilder resultSb = new StringBuilder();
        resultSb.append(text.substring(0, minLength));
        while (getLength(resultSb.toString()) < dbcCaseLength) {
            resultSb.append(text.subSequence(minLength, minLength + 1));
            minLength = minLength + 1;
        }

        if (getLength(resultSb.toString()) > dbcCaseLength) {
            resultSb.deleteCharAt(resultSb.length() - 1);//删除最后一个字符
        }
        return resultSb.toString();
    }

    /**
     * 截取字符串长度(根据所给长度参数)
     *
     * @param str
     * @param length
     * @return String
     */
    public static String truncateStrByFixedLength(String str, int length) {
        Assert.notNull(str, "str must not be null");
        if (length != 0 && str.length() > length) {
            str = str.substring(0, length);
        }
        return str;
    }


    /**
     * 半角、全角字符判断
     *
     * @param c 字符
     * @return true：半角； false：全角
     */
    public static boolean isDbcCase(char c) {
        // 基本拉丁字母（即键盘上可见的，空格、数字、字母、符号）
        if (c >= 32 && c <= 127) {
            return true;
        }
        // 日文半角片假名和符号
        else if (c >= 65377 && c <= 65439) {
            return true;
        }
        return false;
    }

    /**
     * 字符串长度取得（区分半角、全角）
     *
     * @param str 字符串
     * @return 字符串长度
     */
    public static int getLength(String str) {
        int len = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (isDbcCase(c)) { // 半角
                len = len + 1;
            } else { // 全角
                len = len + 2;
            }
        }
        return len;
    }

    /**
     * @param str 以","分割
     * @return
     */
    public static List<Integer> strConvertToIntList(String str) {
        if (!StringUtil.isEmpty(str)) {
            List<Integer> result = new ArrayList<>();
            String[] strs = str.split(",");
            for (int i = 0; i < strs.length; i++) {
                result.add(Integer.valueOf(strs[i]));
            }
            return result;
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * 数组转换成逗号分隔符的字符串
     *
     * @param texts
     * @return
     */
    public static String convertArrayToDotString(String... texts) {
        return convertArrayToStringByDelimiter(",", texts);
    }

    /**
     * list转成逗号分隔的字符串
     *
     * @param list
     * @return String
     */
    public static String convertListToString(List<String> list) {
        return convertArrayToDotString(list.toArray(new String[]{}));
    }

    private static String convertArrayToStringByDelimiter(String delimiter, String... texts) {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < texts.length; i++) {
            if (buffer.length() > 0) {
                buffer.append(delimiter);
            }
            buffer.append(texts[i]);
        }
        return buffer.toString();
    }

    public static String replaceAll(String srcText, String oldChar, String newChar) {
        if (!org.springframework.util.StringUtils.hasText(srcText)) {
            return srcText;
        }

        return srcText.replaceAll(oldChar, newChar);
    }

    public static String list2StrRmBrackets(List<?> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        String jsonString = JSON.toJSONString(list);
        return rmJsonBrackets(jsonString);
    }

    public static String rmJsonBrackets(String jsonStr) {
        return jsonStr.replaceAll("^\\[(.*)\\]$", "$1");
    }

    public static String replaceSpace(String url) {
        return url.replaceAll(" ", "%20");
    }

    public static String getNumberForPK() {
        String id = "";
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        String temp = sf.format(new Date());
        int random = (int) (Math.random() * 10000);
        id = temp + random;
        return id;
    }

    /**
     * <pre>
     * StringUtil.compare(null,null)      = true
     * StringUtil.compare("","")        = true
     * StringUtil.compare(" "," ")       = true
     * StringUtil.compare("bob",null)     = false
     * StringUtil.compare("  bob  ","") = false
     * </pre>
     *
     * @param str1
     * @param str2
     * @return
     * @author xin.feng
     */
    public static boolean compare(String str1, String str2) {
        if (StringUtils.isBlank(str1) && StringUtils.isBlank(str2)) {
            return true;
        }
        return StringUtils.isBlank(str1) ? str2.equals(str1) : str1.equals(str2);
    }

    public static String characterReplace(String s) {
        if (s == null || "".equals(s)) {
            return s;

        }
        s = s.replace("<", "&lt");
        s = s.replace(">", "&gt");
        return s;
    }

    public static String stringArray2String(List<String> str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.size(); i++) {
            sb.append("'").append(str.get(i)).append("'").append(",");
        }
        return sb.toString().substring(0, sb.length() - 1);
    }

    public static String duplicateRemovalArray(String[] arrays) {
        if (arrays == null || arrays.length == 0) {
            return null;
        }
        List<String> rtnExpNoList = new ArrayList<>();
        for (String str : arrays) {
            if (!rtnExpNoList.contains(str)) {
                rtnExpNoList.add(str);
            }
        }
        return convertListToString(rtnExpNoList);
    }

    public static String copySting(String str, Integer i, String delimiter) {
        if (StringUtil.isEmpty(str)) {
            return null;
        }
        StringBuilder copyStr = new StringBuilder();
        for (int j = 0; j < i; j++) {
            if (j == i - 1) {
                copyStr.append(str);
            } else {
                copyStr.append(str).append(delimiter);
            }
        }
        return copyStr.toString();
    }

    /**
     * 忽略字符串中emoji表情
     *
     * @param str
     * @return
     */
    public static String ignoreEmoji(String str) {
        if (isEmpty(str)) {
            return str;
        }
        int index = 0;
        while (index > -1) {
            index = str.indexOf("\uD83D");
            if (index == -1) {
                index = str.indexOf("\uD83C");
            }
            if (index > -1) {
                str = str.substring(0, index) + str.substring(index + 2);
            }
        }
        return str;
    }


    /**
     * jsonArray转List
     *
     * @param jsonArray
     * @return
     */
    public static List<?> jsonArrayToList(String jsonArray) {
        List<?> list = Lists.newArrayList();
        if (StringUtils.isNotEmpty(jsonArray)) {
            Gson gson = new Gson();
            list = gson.fromJson(jsonArray, new TypeToken<List<?>>() {
            }.getType());
        }
        return list;
    }

    /**
     * 判断字符串是否可以转化为json对象
     *
     * @param content
     * @return
     */
    public static boolean isJsonObject(String content) {
        // 此处应该注意，不要使用StringUtils.isEmpty(),因为当content为"  "空格字符串时，JSONObject.parseObject可以解析成功，
        // 实际上，这是没有什么意义的。所以content应该是非空白字符串且不为空，判断是否是JSON数组也是相同的情况。
        if (StringUtils.isBlank(content)) {
            return false;
        }
        try {
            JSONObject jsonStr = JSONObject.parseObject(content);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断字符串是否可以转化为JSON数组
     *
     * @param content
     * @return
     */
    public static boolean isJsonArray(String content) {
        if (StringUtils.isBlank(content)) {
            return false;
        }
        StringUtils.isEmpty(content);
        try {
            JSONArray jsonStr = JSONArray.parseArray(content);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
