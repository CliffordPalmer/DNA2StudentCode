import java.sql.SQLOutput;

/**
 * DNA
 * <p>
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *</p>
 * <p>
 * Completed by: [YOUR NAME HERE]
 *</p>
 */

public class DNA {

    /**
     * TODO: Complete this function, STRCount(), to return longest consecutive run of STR in sequence.
     */
    public static int STRCount(String sequence, String STR) {
        int maxSTR = 0;
        int currentCount = 0;
        long STRHash = hash(STR);
        long chunk = hash(sequence.substring(0, STR.length()));
        for(int i = 1; i < sequence.length() - STR.length(); i ++){
            if(chunk == STRHash) {
                i += STR.length() - 1;
                chunk = hash(sequence.substring(i, i + STR.length()));
                currentCount ++;
            }
            else if(chunk != STRHash){
                chunk = reHash(chunk, sequence.charAt(i + STR.length() - 1));
                if(currentCount > maxSTR){
                    maxSTR = currentCount;
                }
                currentCount = 0;
            }
        }
        if(currentCount > maxSTR){
            maxSTR = currentCount + 1;
        }
        return maxSTR;
    }

    public static long hash(String STR){
        long hash = 0;
        // A = 1, C = 2, T = 3, G = 4
        int length = STR.length();
        for(int i = 0; i < length; i++){
            if(Character.toUpperCase(STR.charAt(length - i - 1)) == 'A'){
                hash += Math.pow(10, i);
            }
            else if(Character.toUpperCase(STR.charAt(length - i - 1))  == 'C'){
                hash += Math.pow(10, i) * 2;
            }
            else if(Character.toUpperCase(STR.charAt(length - i - 1)) == 'T'){
                hash += Math.pow(10, i) * 3;
            }
            else{
                hash += Math.pow(10, i) * 4;
            }
        }
        return hash;
    }
    public static long reHash(long oldHash, char newChar){
        long hash = (oldHash % (int)Math.pow(10, String.valueOf(oldHash).length() - 1)) * 10;
        if(Character.toUpperCase(newChar) == 'A'){
            return hash + 1;
        }
        else if(Character.toUpperCase(newChar) == 'C'){
            return hash + 2;
        }
        else if(Character.toUpperCase(newChar) == 'T'){
            return hash + 3;
        }
        else{
            return hash + 4;
        }
    }
}
