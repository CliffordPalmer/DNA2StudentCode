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
        System.out.println(STRHash);
        long chunk = hash(sequence.substring(0, STR.length()));
        for(int i = 1; i < sequence.length() - STR.length(); i ++){
            if(chunk == STRHash) {
                i += STR.length() - 1;
                chunk = hash(sequence.substring(i, i + STR.length()));
                currentCount ++;
            }
            else if(chunk != STRHash){
                chunk = hash(sequence.substring(i, i + STR.length()));
                       // reHash(chunk, sequence.charAt(i + STR.length() - 1));
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
        long hash = 1;
        // A = 00, C = 01, T = 10, G = 11
        int length = STR.length();
        for(int i = 0; i < length; i++){
            if(Character.toUpperCase(STR.charAt(length - i - 1)) == 'A'){
                hash <<= 2;
            }
            else if(Character.toUpperCase(STR.charAt(length - i - 1))  == 'C'){
                hash <<= 2;
                hash += 1;
            }
            else if(Character.toUpperCase(STR.charAt(length - i - 1)) == 'T'){
                hash <<= 1;
                hash += 1;
                hash <<= 1;
            }
            else{
                hash <<= 1;
                hash += 1;
                hash <<= 1;
                hash += 1;
            }
        }
        return hash;
    }
    public static long reHash(long oldHash, char newChar){
        long hash = (oldHash % (int)Math.pow(10, String.valueOf(oldHash).length() - 1)) * 10;
        if(Character.toUpperCase(newChar) == 'A'){
            return oldHash << 2;
        }
        else if(Character.toUpperCase(newChar) == 'C'){
            return oldHash << 2 + 1;
        }
        else if(Character.toUpperCase(newChar) == 'T'){
            hash <<= 1;
            hash += 1;
            hash <<= 1;
            return hash + 3;
        }
        else{
            hash <<= 1;
            hash += 1;
            hash <<= 1;
            hash +=1;
            return hash + 4;
        }
    }
}
