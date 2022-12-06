public class Hash {
    /**
     * Hash function from Levitin.
     *
     * @param str the string to hash
     * @return returns the hash as a long
     */
    public static int hashString(String str) {
        int h = 0;
        final int C = 123; //some constant bigger than max ASCII char code
        final int m = 293; //size of hash table so indexing works
        for (int i = 0; i < str.length(); i++) {
            h = (h * C + str.charAt(i)) % m;
        }
        return h;
    }
}
