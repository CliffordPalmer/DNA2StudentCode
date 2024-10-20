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

    private static int[] alphabetReference;
    /**
     * TODO: Complete this function, STRCount(), to return longest consecutive run of STR in sequence.
     */
    public static int STRCount(String sequence, String STR) {
        alphabetReference = new int[256];
        alphabetReference['A'] = 0;
        alphabetReference['C'] = 1;
        alphabetReference['T'] = 2;
        alphabetReference['G'] = 3;
        int maxSTR = 0;
        int currentCount = 0;
        long STRHash = hash(STR);
        System.out.println(hash("actg"));
        System.out.println(hash("ctga"));
        System.out.println(reHash(hash("actg"), "actg", 'C'));
        long chunk = hash(sequence.substring(0, STR.length()));
        for(int i = 1; i < sequence.length() - STR.length(); i ++){
            if(chunk == STRHash) {
                i += STR.length() - 1;
                chunk = hash(sequence.substring(i, i + STR.length()));
                currentCount ++;
            }
            else if(chunk != STRHash){
//                chunk = reHash(chunk, sequence.charAt(i + STR.length() - 1));
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
    public static long reHash(long oldHash, String shortSequence, char newLetter){
//        long hash = (oldHash % (int)Math.pow(4, String.valueOf(oldHash).length() - 1));
        long hash = (oldHash) - alphabetReference[Character.toUpperCase(shortSequence.charAt(0))] << (shortSequence.length());
        if(Character.toUpperCase(newLetter) == 'A'){
            return oldHash << 2;
        }
        else if(Character.toUpperCase(newLetter) == 'C'){
            return (oldHash << 2) + 1;
        }
        else if(Character.toUpperCase(newLetter) == 'T'){
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
