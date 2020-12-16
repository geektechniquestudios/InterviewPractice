import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//class Practice2 {
//    public static void main(String[] args) {
//        String str = "aoeu01234stnh0987";
//        Matcher matcher = Pattern.compile("\\p{L}+|[0-9]+").matcher(str);
//        while (matcher.find()) {
//            String substring = matcher.group();
//
//            try {
//                System.out.println(Integer.parseInt(substring));
//            } catch (NumberFormatException e) {
//                System.out.println(substring);
//            }
//        }
//    }
//}

//    private static List<List<String>> fragmentArr(String[] inputArr) {
//        List<List<String>> fragArr = new ArrayList<>();
//        for (String string : inputArr) {
//            char[] charArr = string.toCharArray();
//            StringBuilder tempStr = new StringBuilder();
//            StringBuilder tempInt = new StringBuilder();
//            List<String> listToAdd = new ArrayList<>();
//            boolean wasPrevNum = false; //Character.isDigit(charArr[0]);
//
//            for (char c : charArr) {
//                boolean isNum = Character.isDigit(c);
//                if (isNum) {
//                    tempInt.append(c);
//                    if (!wasPrevNum) {
//                        wasPrevNum = true;
//                        listToAdd.add(tempStr.toString());
//                        tempStr = new StringBuilder();
//                    }
//                } else {
//                    tempStr.append(c);
//                    if (wasPrevNum) {
//                        while (tempInt.charAt(0) == '0' && tempInt.length() > 1) tempInt.deleteCharAt(0);
//                        listToAdd.add(tempInt.toString());
//                        tempInt = new StringBuilder();
//                        wasPrevNum = false;
//                    }
//                }
//            }
//            while (tempInt.charAt(0) == '0' && tempInt.length() > 1) tempInt.deleteCharAt(0);
//            listToAdd.add(wasPrevNum ? tempInt.toString() : tempStr.toString());
//            fragArr.add(listToAdd);
//        }
//        return fragArr;
//    }
//
//    private static String reverseString(String input) {
//        int left = 0;
//        int right = input.length() - 1;
//        char[] chars = input.toCharArray();
//        while (left < right) {
//            char temp = chars[left];
//            chars[left] = chars[right];
//            chars[right] = temp;
//            left++;
//            right--;
//        }
//        return new String(chars);
//        // O(log(n)) because we only have to traverse half of the input array
//    }
//
//    public static boolean abString(char a, char b, String string) {
//        //do all instances of a come before any instance of b
//        final boolean[] hasBeenVisited = {false};
//        AtomicBoolean shouldBeFalse = new AtomicBoolean(false);
//        string.chars().forEach(c -> {
//            if (c == a && hasBeenVisited[0]) shouldBeFalse.set(true);
//            if (c == b) hasBeenVisited[0] = true;
//        });
//        return !shouldBeFalse.get();
//    }
//
//    public static boolean abString2(char a, char b, String string) {
//        boolean hasBeenVisited = false;
//        char[] chars = string.toCharArray();
//        for (char c : chars) {
//            // could improve worst case by doing =>  if rest of arr != contains(a) && hasBeenVisited
//            if (c == a && hasBeenVisited) return false;
//            else if (c == b) hasBeenVisited = true;
//        }
//        return true;
//    }

