package com.xtn.common.utils;

import com.xtn.common.base.Constants;
import lombok.NoArgsConstructor;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

@NoArgsConstructor
public class StringUtil {

    public static String EMPTY = "";
    
    /**
     * 返回不带 - 的UUID
     *
     * @return
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
    
    /**
     * 获取一个随机字符串
     *
     * @return
     */
    public static String getReq() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String time = df.format(new Date());
        return time + (int) (Math.random() * 1000);
    }
    
    /**
     * 密码加密, md5
     *
     * @param password
     * @return
     */
    public static String encryptPassword(String password) {
        if (Objects.isNull(password)) {
            return null;
        }
        try {
            return DigestUtils.md5DigestAsHex(password.getBytes(Constants.DEFAULT_CHARSET));
        } catch (Exception e) {
            throw new RuntimeException("Encrypt password error");
        }
    }

    public static int compare(String source,
                              String target,
                              boolean carecase) {
        if (source == null && target == null) {
            return 0;
        }
        if (source != null && target == null) {
            return 1;
        }
        if (source == null && target != null) {
            return -1;
        } else {
            return carecase ? source.compareTo(target) : source.compareToIgnoreCase(target);
        }
    }

    public static boolean contains(String value,
                                   char search) {
        return value != null && value.indexOf(search) != -1;
    }

    public static boolean contains(String value,
                                   String search) {
        if (value != null && search != null) {
            value = "," + value + ",";
            search = "," + search + ",";
            return value.contains(search);
        } else {
            return false;
        }
    }

    public static int count(String value,
                            char splitter) {
        int count = 0;
        if (value != null) {
            int length = value.length();
            for (int n = 0; n < length; n++) {
                if (splitter == value.charAt(n)) {
                    count++;
                }
            }

        }
        return count;
    }

    public static boolean endsWith(String value,
                                   String search) {
        return value != null && value.endsWith(search);
    }

    public static boolean equals(Object source,
                                 Object target) {
        return source != null && target != null && source.toString().equals(target.toString());
    }

    public static boolean equals(String source,
                                 String target) {
        return source != null && source.equals(target);
    }


    public static String firstLower(String source) {
        if (source != null) {
            if (source.length() > 1) {
                return source.substring(0, 1).toLowerCase() + source.substring(1);
            } else {
                return source.toLowerCase();
            }
        } else {
            return null;
        }
    }

    public static String firstUpper(String source) {
        if (source != null) {
            if (source.length() > 1) {
                return source.substring(0, 1).toUpperCase() + source.substring(1);
            } else {
                return source.toUpperCase();
            }
        } else {
            return null;
        }
    }

    public static final boolean hasChar(String value,
                                        String chars) {
        if (value != null && chars != null) {
            char charArray[] = value.toCharArray();
            int count = charArray.length;
            for (int n = 0; n < count; n++) {
                if (chars.indexOf(charArray[n]) != -1) {
                    return true;
                }
            }

        }
        return false;
    }

    public static final boolean hasChars(String value,
                                         String chars) {
        if (value != null && chars != null) {
            char charArray[] = value.toCharArray();
            int count = charArray.length;
            for (int n = 0; n < count; n++) {
                if (chars.indexOf(charArray[n]) == -1) {
                    return false;
                }
            }

        }
        return true;
    }


    public static final boolean isBlank(String value) {
        return value == null || value.trim().length() == 0;
    }

    public static final boolean isBlank(String... values) {
        for (int n = 0; n < values.length; n++) {
            if (values[n] == null) {
                return true;
            }
            if (values[n].trim().length() == 0) {
                return true;
            }
        }

        return false;
    }


    public static final boolean isEmpty(String value) {
        return value != null ? value.length() == 0 : true;
    }

    public static final boolean isEmpty(String values[]) {
        for (int n = 0; n < values.length; n++) {
            if (values[n] != null && values[n].length() > 0) {
                return false;
            }
        }

        return true;
    }

    public static final boolean isNotBlank(String value) {
        if (value == null) {
            return false;
        }
        return value.trim().length() != 0;
    }

    public static final boolean isNotBlank(String... values) {
        for (int n = 0; n < values.length; n++) {
            if (values[n] == null) {
                return false;
            }
            if (values[n].trim().length() == 0) {
                return false;
            }
        }

        return true;
    }

    public static final boolean isNotEmpty(Object value) {
        if (value == null) {
            return false;
        }
        if (value instanceof String) {
            return ((String) value).length() > 0;
        } else {
            return true;
        }
    }

    public static final boolean isNotEmpty(String values[]) {
        for (int n = 0; n < values.length; n++) {
            if (values[n] == null) {
                return false;
            }
            if (values[n].length() == 0) {
                return false;
            }
        }

        return true;
    }

    public static final boolean isNotEmpty(String value) {
        return value != null ? value.length() > 0 : false;
    }

    public static final String join(String left,
                                    String splitter,
                                    String right) {
        if (isEmpty(left) && isEmpty(right)) {
            return EMPTY;
        }
        if (!isEmpty(left) && isEmpty(right)) {
            return left;
        }
        if (isEmpty(left) && !isEmpty(right)) {
            return right;
        } else {
            return (new StringBuilder(String.valueOf(left))).append(splitter).append(right).toString();
        }
    }

    public static final String join(String values[]) {
        return join(values, EMPTY);
    }


    public static final String join(String arData[],
                                    String sSplitter) {
        if (arData != null) {
            int nLength = arData.length;
            StringBuffer oResult = new StringBuffer();
            for (int n = 0; n < nLength; n++) {
                if (n != 0) {
                    oResult.append(sSplitter);
                }
                if (!isEmpty(arData[n])) {
                    oResult.append(arData[n]);
                }
            }

            return oResult.toString();
        } else {
            return null;
        }
    }

    public static final String join(String values[],
                                    String splitter,
                                    String left,
                                    String right) {
        if (values != null) {
            int length = values.length;
            StringBuffer result = new StringBuffer();
            for (int n = 0; n < length; n++) {
                if (n != 0) {
                    result.append(splitter);
                }
                if (!isEmpty(values[n])) {
                    result.append(left);
                    result.append(values[n]);
                    result.append(right);
                }
            }

            return result.toString();
        } else {
            return null;
        }
    }

    public static final String left(String sValue,
                                    int nLength) {
        if (sValue != null) {
            if (sValue.length() > nLength) {
                return sValue.substring(0, nLength);
            } else {
                return sValue;
            }
        } else {
            return null;
        }
    }

    public static final String left(String sValue,
                                    String sSearch) {
        if (sValue != null) {
            int nIndex = sValue.indexOf(sSearch);
            if (nIndex >= 0) {
                return sValue.substring(0, nIndex);
            } else {
                return sValue;
            }
        } else {
            return null;
        }
    }

    public static final String leftBytePad(String sValue,
                                           int nLength) {
        return leftBytePad(sValue, nLength, " ");
    }

    public static final String leftBytePad(String sValue,
                                           int nLength,
                                           String sChar) {
        if (sValue == null) {
            return "";
        }
        int nLoop = nLength - sValue.getBytes().length;
        if (nLoop > 0) {
            StringBuffer oResult = new StringBuffer();
            for (int n = 0; n < nLoop; n++) {
                oResult.append(sChar);
            }

            oResult.append(sValue);
            return oResult.toString();
        } else {
            return sValue;
        }
    }

    public static final String makeDefaultValue(String sValue,
                                                String sDefault) {
        return isNotEmpty(sValue) ? sValue : sDefault;
    }

    public static boolean matchString(String value,
                                      String search) {
        if (value != null && search != null) {
            boolean start = search.startsWith("*");
            boolean end = search.endsWith("*");
            if (start && end) {
                return value.indexOf(search.substring(1, search.length() - 1)) > 0;
            }
            if (start) {
                return value.endsWith(search.substring(1));
            }
            if (end) {
                return value.startsWith(search.substring(0, search.length() - 1));
            } else {
                return value.equals(search);
            }
        } else {
            return false;
        }
    }

    public static String notEmptyValue(Object oValue) {
        return oValue == null ? "" : oValue.toString();
    }

    public static String notEmptyValue(Object oValue,
                                       Object oDefault) {
        if (oValue == null) {
            return oDefault.toString();
        } else {
            String sValue = oValue.toString();
            return sValue.length() <= 0 ? oDefault.toString() : sValue;
        }
    }

    public static String notEmptyValue(String sValue,
                                       String sDefault) {
        return sValue == null ? sDefault : sValue.length() <= 0 ? sDefault : sValue;
    }

    public static String nvl(Object value,
                             Object other) {
        if (value != null) {
            String result = value.toString();
            if (result.length() > 0) {
                return result;
            }
        }
        if (other != null) {
            return other.toString();
        } else {
            return EMPTY;
        }
    }

    public static String nvl(String value) {
        return value == null ? EMPTY : value.length() <= 0 ? EMPTY : value;
    }

    public static String nvl(String values[]) {
        if (values != null) {
            for (int n = 0; n < values.length; n++) {
                if (values[n] != null && values[n].length() > 0) {
                    return values[n];
                }
            }

        }
        return EMPTY;
    }

    public static String nvl(String value,
                             String other) {
        if (value != null && value.length() > 0) {
            return value;
        }
        if (other != null) {
            return other;
        } else {
            return EMPTY;
        }
    }

    public static final String parse(String sValue,
                                     String sKey,
                                     int nSubValue) {
        return replace(sValue, (new StringBuilder("{")).append(sKey.toLowerCase()).append("}").toString(), Integer.toString(nSubValue));
    }

    public static final String parse(String sValue,
                                     String sKey,
                                     String sSubValue) {
        return replace(sValue, (new StringBuilder("{")).append(sKey.toLowerCase()).append("}").toString(), sSubValue);
    }

    public static final String parseParams(String context,
                                           String params[]) {
        if (params != null) {
            int count = params.length;
            for (int n = 1; n <= count; n++) {
                context = replace(context, (new StringBuilder("{")).append(n).append("}").toString(), params[n - 1]);
            }

        }
        return context;
    }

    public static final String removeChars(String sValue,
                                           String sChars) {
        if (sValue != null && sChars != null) {
            StringBuffer oResult = new StringBuffer();
            char arChars[] = sValue.toCharArray();
            int nCount = arChars.length;
            for (int n = 0; n < nCount; n++) {
                if (sChars.indexOf(arChars[n]) < 0) {
                    oResult.append(arChars[n]);
                }
            }

            return oResult.toString();
        } else {
            return null;
        }
    }

    public static final String removeString(String sValue,
                                            String sSubString) {
        if (sValue == null || sSubString == null) {
            return null;
        }
        int nFind = sValue.indexOf(sSubString);
        if (nFind != -1) {
            int nLength = sSubString.length();
            return (new StringBuilder(String.valueOf(sValue.substring(0, nFind)))).append(sValue.substring(nFind + nLength)).toString();
        } else {
            return null;
        }
    }

    public static final String removeRepeat(String sValue) {
        if (isBlank(sValue)) {
            return "";
        }
        String strs[] = split(sValue, ",");
        Set<String> set = new TreeSet<String>();
        for (int i = 0; i < strs.length; i++) {
            set.add(strs[i]);
        }

        strs = (String[]) set.toArray(new String[0]);
        StringBuffer sb = new StringBuffer();
        int len = strs.length;
        for (int i = 0; i < len; i++) {
            sb.append(strs[i]);
            if (i != len - 1) {
                sb.append(",");
            }
        }

        return sb.toString();
    }

    public static final String repeat(String value,
                                      int count) {
        if (value != null) {
            StringBuffer result = new StringBuffer();
            for (int n = 0; n < count; n++) {
                result.append(value);
            }

            return result.toString();
        } else {
            return EMPTY;
        }
    }

    public static final String replace(String value,
                                       char from,
                                       char to) {
        if (value != null) {
            char arChars[] = value.toCharArray();
            int nCount = arChars.length;
            for (int n = 0; n < nCount; n++) {
                if (arChars[n] == from) {
                    arChars[n] = to;
                }
            }

            return new String(arChars);
        } else {
            return null;
        }
    }

    public static final String replace(String value,
                                       String from,
                                       String to) {
        return replace(value, from, to, true, -1);
    }

    public static final String replace(String value,
                                       String from,
                                       String to,
                                       boolean careCase) {
        return replace(value, from, to, careCase, -1);
    }

    public static final String replace(String value,
                                       String from,
                                       String to,
                                       boolean careCase,
                                       int count) {
        if (value != null && from != null) {
            if (to == null) {
                to = "";
            }
            int pos = 0;
            int find = -1;
            int fromLength = from.length();
            StringBuffer result = new StringBuffer();
            if (careCase) {
                while ((find = value.indexOf(from, pos)) != -1) {
                    result.append(value.substring(pos, find));
                    result.append(to);
                    pos = find + fromLength;
                    if (count > 0 && --count == 0) {
                        break;
                    }
                }
                result.append(value.substring(pos));
            } else {
                String tmpSource = value.toLowerCase();
                String tmpFrom = from.toLowerCase();
                while ((find = tmpSource.indexOf(tmpFrom, pos)) != -1) {
                    result.append(value.substring(pos, find));
                    result.append(to);
                    pos = find + fromLength;
                    if (count > 0 && --count == 0) {
                        break;
                    }
                }
                result.append(value.substring(pos));
            }
            return result.toString();
        } else {
            return null;
        }
    }

    public static final String replaceChar(String value,
                                           char from,
                                           char to) {
        if (value != null) {
            char chars[] = value.toCharArray();
            int length = value.length();
            for (int n = 0; n < length; n++) {
                if (chars[n] == from) {
                    chars[n] = to;
                }
            }

            return new String(chars);
        } else {
            return value;
        }
    }

    public static final String replaceChars(String value,
                                            char replaces[]) {
        if (value != null && replaces != null) {
            int rl = replaces.length;
            if (rl % 2 != 0) {
                throw new IllegalArgumentException("Must pairs.");
            }
            int p = -1;
            char chars[] = value.toCharArray();
            for (int length = value.length(); ++p < length; ) {
                for (int n = -2; (n += 2) < rl; ) {
                    if (chars[p] == replaces[n]) {
                        chars[p] = replaces[n + 1];
                        break;
                    }
                }

            }

            return new String(chars);
        } else {
            return value;
        }
    }

    public static final String replaceChars(String sValue,
                                            String sChars,
                                            String sChar) {
        if (sValue != null && sChars != null) {
            if (sChar == null) {
                sChar = "";
            }
            StringBuffer oResult = new StringBuffer();
            char arChars[] = sValue.toCharArray();
            int nCount = arChars.length;
            for (int n = 0; n < nCount; n++) {
                if (sChars.indexOf(arChars[n]) == -1) {
                    oResult.append(arChars[n]);
                } else {
                    oResult.append(sChar);
                }
            }

            return oResult.toString();
        } else {
            return null;
        }
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte[] buf) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0, j = buf.length; i < j; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (!StringUtils.hasText(hexStr)) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0, j = hexStr.length(); i < j / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i << 1, (i << 1) + 1), 16);
            int low = Integer.parseInt(hexStr.substring((i << 1) + 1, (i << 1) + 2), 16);
            result[i] = (byte) ((high << 4) + low);
        }
        return result;
    }

    public static final String replaceSearch(String sValue,
                                             String sSearch,
                                             String sLeft,
                                             String sRight) {
        return replaceSearch(sValue, sSearch, sLeft, sRight, true, -1);
    }

    public static final String replaceSearch(String sValue,
                                             String sSearch,
                                             String sLeft,
                                             String sRight,
                                             boolean bCareCase) {
        return replaceSearch(sValue, sSearch, sLeft, sRight, bCareCase, -1);
    }

    public static final String replaceSearch(String sValue,
                                             String sSearch,
                                             String sLeft,
                                             String sRight,
                                             boolean bCareCase,
                                             int nReplace) {
        if (sValue != null && sSearch != null) {
            if (sLeft == null) {
                sLeft = "";
            }
            if (sRight == null) {
                sRight = "";
            }
            int nPosition = 0;
            int nFindPosition = -1;
            int nSearchLength = sSearch.length();
            StringBuffer oResult = new StringBuffer();
            if (bCareCase) {
                do {
                    nFindPosition = sValue.indexOf(sSearch, nPosition);
                    if (nFindPosition == -1) {
                        break;
                    }
                    oResult.append(sLeft);
                    oResult.append(sValue.substring(nPosition, nFindPosition));
                    oResult.append(sRight);
                    nPosition = nFindPosition + nSearchLength;
                } while (nReplace <= 0 || --nReplace != 0);
                oResult.append(sValue.substring(nPosition));
            } else {
                String sTmpValue = sValue.toLowerCase();
                String sTmpFrom = sSearch.toLowerCase();
                do {
                    nFindPosition = sTmpValue.indexOf(sTmpFrom, nPosition);
                    if (nFindPosition == -1) {
                        break;
                    }
                    oResult.append(sLeft);
                    oResult.append(sValue.substring(nPosition, nFindPosition));
                    oResult.append(sRight);
                    nPosition = nFindPosition + nSearchLength;
                } while (nReplace <= 0 || --nReplace != 0);
                oResult.append(sValue.substring(nPosition));
            }
            return oResult.toString();
        } else {
            return null;
        }
    }

    public static final String right(String sValue,
                                     int nLength) {
        if (sValue != null) {
            int nValueLength = sValue.length();
            if (nValueLength > nLength) {
                return sValue.substring(nValueLength - nLength);
            } else {
                return sValue;
            }
        } else {
            return null;
        }
    }

    public static final String right(String sValue,
                                     String sSearch) {
        if (sValue != null) {
            int nIndex = sValue.lastIndexOf(sSearch);
            if (nIndex >= 0) {
                return sValue.substring(nIndex + sSearch.length());
            } else {
                return sValue;
            }
        } else {
            return null;
        }
    }

    public static String singleLine(String source) {
        if (source != null) {
            for (source = replaceChars(source, new char[]{'\r', ' ', '\n', ' '}); source.indexOf("  ") != -1; source = replace(source, "  ", " ")) {
                ;
            }
        }
        return source;
    }

    public static String[] split(String value,
                                 char split) {
        if (value == null) {
            return new String[0];
        }
        int length = value.length();
        char chars[] = new char[length];
        value.getChars(0, length, chars, 0);
        int blockSize = 1;
        for (int n = 0; n < length; n++) {
            if (chars[n] == split) {
                blockSize++;
            }
        }

        String blocks[] = new String[blockSize];
        if (blockSize > 1) {
            blockSize = 0;
            int pos = 0;
            for (int n = 0; n < length; n++) {
                if (chars[n] == split) {
                    blocks[blockSize++] = new String(chars, pos, n - pos);
                    pos = n + 1;
                }
            }

            if (pos < length) {
                blocks[blockSize] = new String(chars, pos, length - pos);
            }
        } else {
            blocks[0] = value;
        }
        return blocks;
    }

    public static String[] split(String value,
                                 char split,
                                 int limit) {
        int length = value.length();
        char memory[] = new char[length];
        value.getChars(0, length, memory, 0);
        int blockSize = 1;
        for (int n = 0; n < length; n++) {
            if (memory[n] == split && ++blockSize >= limit) {
                break;
            }
        }

        String blocks[] = new String[blockSize];
        if (blockSize > 1) {
            blockSize = 0;
            int pos = 0;
            for (int n = 0; n < length; n++) {
                if (memory[n] != split) {
                    continue;
                }
                if (blockSize >= limit - 1) {
                    break;
                }
                blocks[blockSize++] = new String(memory, pos, n - pos);
                pos = n + 1;
            }

            if (pos < length) {
                blocks[blockSize] = new String(memory, pos, length - pos);
            }
        } else {
            blocks[0] = value;
        }
        return blocks;
    }

    public static final String[] split(String sValue,
                                       int nLength) {
        if (isEmpty(sValue)) {
            return null;
        }
        int nValueLength = sValue.length();
        int nSize = nValueLength / nLength;
        if (nValueLength % nLength != 0) {
            nSize++;
        }
        String arResult[] = new String[nSize];
        int nPosition = 0;
        int n = 0;
        do {
            if (nPosition + nLength > nValueLength) {
                if (nPosition < nValueLength) {
                    arResult[n] = sValue.substring(nPosition);
                }
                break;
            }
            arResult[n] = sValue.substring(nPosition, nPosition + nLength);
            nPosition += nLength;
            n++;
        } while (true);
        return arResult;
    }

    public static final String[] split(String value,
                                       String split) {
        return split(value, split, -1);
    }

    public static final String[] split(String value,
                                       String split,
                                       int limit) {
        if (value != null && split != null) {
            if (limit > 0) {
                return value.split(split, limit);
            } else {
                return value.split(split);
            }
        } else {
            return new String[0];
        }
    }

    public static final boolean startsWith(String source,
                                           String value) {
        return source != null && source.startsWith(value);
    }

    public static String subParttenValue(String sValue,
                                         String sPartten,
                                         String sSubPartten) {
        if (sValue == null || sPartten == null || sSubPartten == null) {
            return null;
        } else {
            int nFind = sPartten.indexOf(sSubPartten);
            return nFind == -1 ? null : sValue.substring(nFind, nFind + sSubPartten.length());
        }
    }

    public static final String subString(String source,
                                         String left,
                                         String right) {
        if (source != null) {
            int nLeft = -1;
            int nLeftLength = 0;
            if (left != null) {
                nLeft = source.indexOf(left);
                nLeftLength = left.length();
            }
            int nRight = -1;
            if (right != null) {
                if (nLeft < 0) {
                    nRight = source.indexOf(right);
                } else {
                    nRight = source.indexOf(right, nLeft + nLeftLength);
                }
            }
            if (nLeft >= 0 && nRight >= 0) {
                return source.substring(nLeft + nLeftLength, nRight);
            }
            if (nLeft >= 0 && nRight < 0) {
                return source.substring(nLeft + nLeftLength);
            }
            if (nLeft < 0 && nRight >= 0) {
                return source.substring(0, nRight);
            } else {
                return source;
            }
        } else {
            return null;
        }
    }


    public static String toString(Object value) {
        if (value != null) {
            if (value instanceof String) {
                return (String) value;
            } else {
                return value.toString();
            }
        } else {
            return null;
        }
    }


    public static final String format(String source,
                                      Object... params) {
        if (params != null) {
            int count = params.length;
            for (int n = 0; n < count; n++) {
                String info = null;
                Object param = params[n];
                if (param != null) {
                    info = toString(param);
                } else {
                    info = EMPTY;
                }
                source = replace(source, (new StringBuilder("{")).append(n).append("}").toString(), info);
            }

        }
        return source;
    }


}
