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
        // Map for constant time lookup of hash values
        alphabetReference = new int[256];
        alphabetReference['A'] = 0;
        alphabetReference['C'] = 1;
        alphabetReference['T'] = 2;
        alphabetReference['G'] = 3;
        int maxSTR = 0;
        int currentCount = 0;
        int STRLength = STR.length();
        int sequenceLength = sequence.length();
        // Hash STR and first chunk of sequence
        long STRHash = hash(STR, STRLength);
        long hashedChunk = hash(sequence.substring(0, STRLength), STRLength);
        // Loop through the rest of the sequence
        for(int i = 1; i < sequenceLength - STRLength; i ++){
            // If the chunk matches STR
            if(hashedChunk == STRHash) {
                // Skip forward by the length of STR - 1
                i += STRLength - 1;
                // Advance the chunk by 1 letter
                hashedChunk = hash(sequence.substring(i, i + STRLength), STRLength);
                // Add to the count
                currentCount ++;
            }
            // If the chunk doesn't match STR
            else{
                // Advance the chunk by 1 letter
                hashedChunk = reHash(hashedChunk, sequence.charAt(i - 1), sequence.charAt(i + STRLength - 1), STRLength);
                // If there's a new max, set it
                if(currentCount > maxSTR){
                    maxSTR = currentCount;
                }
                // Reset the count
                currentCount = 0;
            }
        }
        // Handles the edge case where the very last part of the sequence is the largest repeat
        if(currentCount > maxSTR){
            maxSTR = currentCount + 1;
        }
        return maxSTR;
    }

    // Hash function
    public static long hash(String STR, int STRLength){
        // Long to store hash, because hash might exceed Integer.MAX_VALUE
        long hash = 0;
        // Loop through sequence, hashing one letter at a time
        for(int i = 0; i < STRLength; i++){
            // Bit shifting for speed, equivalent to multiplying by a radix of 4
            hash <<= 2;
            hash += alphabetReference[Character.toUpperCase(STR.charAt(i))];
        }
        return hash;
    }
    // Method which creates a new hash by removing the first letter, and adding another
    public static long reHash(long oldHash, char toRemove, char newLetter, int chunkLength){
        // Subtract the value of the character being removed
        long hash = oldHash - (alphabetReference[Character.toUpperCase(toRemove)] << (2 * (chunkLength - 1)));
        // Then add the value of the new character, and return
        return (hash << 2) + alphabetReference[newLetter];
    }
}
