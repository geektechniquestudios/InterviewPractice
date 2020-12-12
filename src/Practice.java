import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

public class Practice {
    public static void main(String[] args) {
        //System.out.println(reverseString("reverse this"));
        //System.out.println(abString('a', 'b', "aaaoeuaabbbbeuid"));
        isSorted(new String[]{"aoeu1234stnh0987", "aoeu1234snthoeui"});
    }

    private static String reverseString(String input) {
        int left = 0;
        int right = input.length() - 1;
        char[] chars = input.toCharArray();
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        return new String(chars);
        // O(log(n)) because we only have to traverse half of the input array
    }

    public static boolean abString(char a, char b, String string) {
        //do all instances of a come before any instance of b
        final boolean[] hasBeenVisited = {false};
        AtomicBoolean shouldBeFalse = new AtomicBoolean(false);
        string.chars().forEach(c -> {
            if (c == a && hasBeenVisited[0]) shouldBeFalse.set(true);
            if (c == b) hasBeenVisited[0] = true;
        });
        return !shouldBeFalse.get();
    }

    public static boolean abString2(char a, char b, String string){
        boolean hasBeenVisited = false;
        char[] chars = string.toCharArray();
        for(char c : chars){
            if (c == a && hasBeenVisited) return false;
            else if (c == b) hasBeenVisited = true;
        }
        return true;
    }

    public static boolean isSorted(String[] arr){
        //very similar to alien language problem
        //difference is that alien lang problem doesn't fragment strings
        //break array into another dimension based on whether int or string
            // ["abc123dce", "456fgh1"] => [["abc", "123", "dce"], ["456", "fgh", "1"]]
        //reduce compare (a, b) => {
        // which arr has lower len
        // for loop over shorter array(i => i[a] <= i[b])
        // if any are false, return false else true
        //}

        List<List<String>> fragArr = fragmentArr(arr);
        System.out.println(fragArr);
        return true;
        //return IntStream.range(1, arr.length).allMatch(i -> compareWords(fragArr.get(i), fragArr.get(i - 1)));

    }

    private static List<List<String>> fragmentArr(String[] inputArr){
        List<List<String>> fragArr = new ArrayList<>();
        //enum wasLastNumOrString
        for (String string : inputArr){
            //break string into char arr,
            //temp var
            //itr char arr
                //if char is same as last time, add to temp
                //elif char diff,
                    //add current temp to fragArr, temp = ""\

            char[] charArr = string.toCharArray();
            StringBuilder tempStr = new StringBuilder();
            StringBuilder tempInt = new StringBuilder();
            List<String> tempList = new ArrayList<>();
            boolean wasPrevNum = false;

            for (char c : charArr){
                //determine if c is num or char
                boolean isNum = Character.isDigit(c);
                if (isNum) {
                    tempInt.append(c);
                    if (!wasPrevNum) {
                        wasPrevNum = true;
                        tempList.add(tempStr.toString());
                        tempStr = new StringBuilder();
                    }
                } else {
                    tempStr.append(c);
                    if(wasPrevNum) {
                        //@todo trim running 0s
                        tempList.add(tempInt.toString());
                        tempInt = new StringBuilder();
                        wasPrevNum = false;
                    }
                }
            }
            fragArr.add(tempList);
        }
        return fragArr;
    }

    private static boolean compareWords(List<String> word1, List<String> word2){
        //ensure word1 < word2
        for (int i = 0; i < Math.min(word1.size(), word2.size()); i++){
            if (word1.get(i).compareTo(word2.get(i)) < 0) return false;
        }
        return true;
    }

    //note to self: @todo convert strings to asci or something
    //don't forget to deal with leading 0s. should probably convert into int for comparison, like if Integer.valueOf() doesn't throw error


}
