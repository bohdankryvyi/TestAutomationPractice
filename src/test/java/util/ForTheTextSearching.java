package util;

import java.util.List;

/**
 * Contains checks whether word is existed in the String
 * AllMatch returns true when all elements pass Predicate filter
 */
public class ForTheTextSearching {
    /**
     * Method which analyse search results
     * @param text (Product names which were founded)
     * @param word (keyword which user used for searching)
     * @return boolean
     */
    public static boolean StreamForTheText(List<String> text, String word) {
        return text.stream().map(String::toLowerCase).allMatch(words -> words.contains(word));
    }
}


/**
 * Next code is a first attempt. DEPRECATED
 * Method get List (List<String>) with Web elements get from findElements() method
 * Then we are creating a list where we are set each title, split it into separate words, lowercase it and
 * check whether at least one word is equal to the word we are searching for
 * if no then make count value <0 which
 * Clear lists and check each title
 * If we have count<0 at the end of the method then it means that at least 1 title was without word that we are searching
 * and we return false. Test will be failed.
 */
/* int wordCount = 0;
        int count = 0;
        List<String> poem = new ArrayList<>();
        for (String words : text) {
            poem.addAll(Arrays.asList(words.split("\\s")));
            //make all words lowercase to make it better searched
            List<String> convertedList = poem.stream().map(wordline -> wordline.toLowerCase()).collect(Collectors.toList());

            for (String itemName : convertedList) {
                if (itemName.equals("printed")) {
                    wordCount++;
                }
            }
            if (wordCount <= 0) {
                count = count - 2147483646;
            }
            convertedList.clear();
            poem.clear();
        }
        if (count == 0) {
            return true;
        }
        return false;*/
