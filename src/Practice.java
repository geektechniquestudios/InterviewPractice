import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

@Slf4j
public class Practice {
    public static void main(String[] args) {
        //System.out.println(reverseString("reverse this"));
        //System.out.println(abString('a', 'b', "aaaoeuaabbbbeuid"));
        //sample data is wrong because 8894 893 8894
        //"Shorter, but identical order numbers come first" So there's no way the last item in the array could be correct because the first group is abc, second is a
        String[] shoudlBeTrue = new String[]{"abc1hdj8894f9-45hj", "abc954hdj8894f9-45hj", "abc01234hdj8894f9-45hj", "abc1234hdj8894f9-45hj",
                "abc1234hdj8894f9-45hj", "abc1234hdj8894f9-45hj", "abc1234hdj8894f9-45hj", "abc1234hdj8894f9-45hj", "abc1234hdj8894f9-45hj", "abc1234hdj8894f9-45hj", "abc012345hdj8894f9-45hj", "abc012345hdj8894f9-45hj"};
        //System.out.println(isSorted(shoudlBeTrue));
        System.out.println(isSorted(new String[]{"000001000", "1"}));
        //System.out.println(isSorted(new String[]{"abc1hdj8894f9-45hj", "abc954hdj8894f9-45hj"}));//, "test31abd"}));
        //System.out.println(isSorted(new String[]{"abc", "123"}));
    }

    public static boolean isSorted(String[] arr) {
        //very similar to alien language problem
        //difference is that alien lang problem doesn't fragment strings,
        //break array into another dimension based on whether int or string
        // ["abc123dce", "456fgh1"] => [["abc", "123", "dce"], ["456", "fgh", "1"]]
        //reduce compare (a, b) => {
        // which arr has lower len
        // for loop over shorter array(i => i[a] <= i[b])
        // if any are false, return false else true
        //}

        //edge case "If there are no items in the list the function should return true as it is properly sorted."
        //if (arr.length == 0) return true; //probably not needed

        List<List<String>> fragArr = fragmentArr(arr);
        return IntStream.range(1, arr.length).parallel()
                .allMatch(i -> compareWords(fragArr.get(i - 1), fragArr.get(i)));
    }

    private static List<List<String>> fragmentArr(String[] inputArr) {
        List<List<String>> fragArr = new ArrayList<>();
        Arrays.stream(inputArr).forEach(string -> {
            List<String> listToAdd = new ArrayList<>();
            Matcher matcher = Pattern.compile("[^0-9]+|[0-9]+").matcher(string);
            while (matcher.find()) {
                String substring = matcher.group();
                listToAdd.add(substring);
            }
            fragArr.add(listToAdd);
        });
        return fragArr;
    }

    private static boolean compareWords(List<String> word1, List<String> word2) {
        return IntStream.range(0, Math.min(word1.size(), word2.size())).parallel()
                .allMatch(i -> compareSubstrings(word1.get(i), word2.get(i)));
    }

    private static boolean compareSubstrings(String word1, String word2) {
        System.out.println(word1 + " " + word2);
        boolean isWord1int = false;
        boolean isWord2int = false;

        if (word1.matches("\\d+")) isWord1int = true;
        if (word2.matches("\\d+")) isWord2int = true;
        //if both are #, compare numbers
        if (isWord1int && isWord2int) return (Integer.parseInt(word1) <= Integer.parseInt(word2));
        if (isWord1int) return false;//char before #
        if (isWord2int) return true;
        return !(word2.compareTo(word1) < 0);
    }

    @Test
    public void outerTests() {
        String[] shoudlBeTrue = new String[]{"abc1hdj8894f9-45hj", "abc954hdj8894f9-45hj", "abc01234hdj8894f9-45hj", "abc1234hdj8894f9-45hj",
                "abc1234hdj8894f9-45hj", "abc1234hdj8894f9-45hj", "abc1234hdj8894f9-45hj", "abc1234hdj8894f9-45hj", "abc1234hdj8894f9-45hj", "abc1234hdj8894f9-45hj", "abc012345hdj8894f9-45hj", "abc012345hdj8894f9-45hj"};
        //String[] shouldBeFalse = new String[]{"abc1hdj8894f9-45hj", "xbc1234hdj8894f9-45hj"};
        assertTrue(isSorted(shoudlBeTrue));
        assertTrue(isSorted(new String[]{"abc-123dce456, abc-0123dce456"}));// Some general tests
        assertTrue(isSorted(new String[]{"a", "b", "c"}));
        assertTrue(isSorted(new String[]{"12", "13", "22"}));
        assertTrue(isSorted(new String[]{"-aaaaaaaaaaaaaaaaaaa", "-aaaaaaaaaaaaaaaaaaa"}));
        assertTrue(isSorted(new String[]{"aaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaab"}));
        assertTrue(isSorted(new String[]{"aaaaaaaaaaaaaaaaaab", "aaaaaaaaaaaaaaaaaab"}));
        assertTrue(isSorted(new String[]{"aaaaaaaaaaaaaaaaaab", "aaaaaaaaaaaaaaaaaab", "b"}));
        assertTrue(isSorted(new String[]{}));// From prompt => If there are no items in the list the function should return true as it is properly sorted.
        assertTrue(isSorted(new String[]{"a"})); // single item
        assertTrue(isSorted(new String[]{"1", "100", "00101"}));
        assertTrue(isSorted(new String[]{"abc", "abcd", "abcde"})); // From prompt => Be aware of comparing strings of different lenghts
        assertTrue(isSorted(new String[]{"abc", "123"}));// From examples => abc should sort before 123.
        assertTrue(isSorted(new String[]{"945", "01234"}));// From examples => 945 should sort before 01234.
        assertTrue(isSorted(new String[]{"abc", "xyz"}));// From examples => abc should sort before xyz.
        assertTrue(isSorted(new String[]{"ayz", "byz"}));// From examples => ayz should sort before byz.
        assertTrue(isSorted(new String[]{"aaaaaaaaaaa-aaaaaaaab", "1"}));// From the question => Any alpha or'-' characters should sort before numeric characters.
        assertTrue(isSorted(new String[]{"-1", "--1", "--1", "-----------1", "1"}));// Messing with dashes
        assertFalse(isSorted(new String[]{"000001000", "1"}));// From prompt => Be aware of comparing strings of different lenghts or numbers padded with Os @todo
        assertFalse(isSorted(new String[]{"1", "aaaaaaaaaaa-aaaaaaaab"}));// From the question => Any alpha or'-' characters should sort before numeric characters.
        assertFalse(isSorted(new String[]{"b", "a"}));
        assertFalse(isSorted(new String[]{"123", "abc"}));// negatives from example
        assertFalse(isSorted(new String[]{"01234", "945"}));
        assertFalse(isSorted(new String[]{"xyz", "abc"}));
        assertFalse(isSorted(new String[]{"byz", "ayz"}));
        //assertFalse(isSorted(shouldBeFalse));
        //assertFalse(isSorted(new String[]{""}));

    }

    @Test
    public void fragmentTest() {
        assertEquals(
                Arrays.asList(
                        Arrays.asList("abc", "123", "dce", "456"),
                        Arrays.asList("123", "abcde", "444", "a")
                ),
                fragmentArr(new String[]{"abc123dce456", "123abcde444a"})

        );
        assertEquals(
                Arrays.asList(Arrays.asList("----", "4", "----")),
                fragmentArr(new String[]{"----4----"})
        );
    }

    @Test
    public void compareWordsTest() {
        assertTrue(
                compareWords(
                        Arrays.asList("abc", "123", "dce", "456"),
                        Arrays.asList("abd", "0000400", "f", "456")
                )
        );
        assertTrue(
                compareWords(
                        Arrays.asList("abc", "123", "dce", "456"),
                        Arrays.asList("abd", "0000400", "fba", "456")
                )
        );
        assertFalse(
                compareWords(
                        Arrays.asList("bac", "123", "dce", "456"),
                        Arrays.asList("abd", "0000400", "faaaaa", "456")
                )
        );
        assertFalse(
                compareWords(
                        Arrays.asList("123"),
                        Arrays.asList("abc")
                )
        );
        assertFalse(
                compareWords(
                        Arrays.asList("000001000"),
                        Arrays.asList("1")
                )
        );
    }

    @Test
    public void compareSubstringsTest() {
        assertTrue(compareSubstrings("abc", "123"));// from example
        assertTrue(compareSubstrings("945", "01234"));
        assertTrue(compareSubstrings("abc", "xyz"));
        assertTrue(compareSubstrings("ayz", "byz"));
        assertFalse(compareSubstrings("123", "abc"));// negatives from example
        assertFalse(compareSubstrings("01234", "945"));
        assertFalse(compareSubstrings("xyz", "abc"));
        assertFalse(compareSubstrings("byz", "ayz"));
        assertFalse(compareSubstrings("000001000", "1"));

    }

}
