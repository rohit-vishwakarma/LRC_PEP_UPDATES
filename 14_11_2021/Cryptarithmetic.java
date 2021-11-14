
import java.io.*;
import java.util.*;

public class Cryptarithmetic {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String s1 = scn.nextLine();
        String s2 = scn.nextLine();
        String s3 = scn.nextLine();
    
        HashMap<Character, Integer> charIntMap = new HashMap<>();
        String unique = "";
        for (int i = 0; i < s1.length(); i++) {
          if (!charIntMap.containsKey(s1.charAt(i))) {
            charIntMap.put(s1.charAt(i), -1);
            unique += s1.charAt(i);
          }
        }
    
        for (int i = 0; i < s2.length(); i++) {
          if (!charIntMap.containsKey(s2.charAt(i))) {
            charIntMap.put(s2.charAt(i), -1);
            unique += s2.charAt(i);
          }
        }
    
        for (int i = 0; i < s3.length(); i++) {
          if (!charIntMap.containsKey(s3.charAt(i))) {
            charIntMap.put(s3.charAt(i), -1);
            unique += s3.charAt(i);
          }
        }
    
        boolean[] usedNumbers = new boolean[10];
        solution(unique, 0, charIntMap, usedNumbers, s1, s2, s3);
      }
      
      public static int numberTostring(HashMap<Character, Integer> hm, String s){
          int res = 0;
          for(int i=0; i<s.length(); i++){
              res = res * 10 + hm.get(s.charAt(i));
          }
          return res;
      }
    
      
      public static void solution(String unique, int idx, HashMap<Character, Integer> hm, boolean[] usedNumbers, String s1, String s2, String s3) {
            // write your code here
            if(idx == unique.length()){
                int x = numberTostring(hm, s1);
                int y = numberTostring(hm, s2);
                int z = numberTostring(hm, s3);
                
                if(x + y == z ){
                    StringBuilder sb = new StringBuilder();
                    for(int i= 'a' ; i<='z'; i++){
                        if(hm.get((char)i) != null)
                        sb.append((char)i +"-" + hm.get((char)i) +" ");
                    }
                    System.out.println(sb.toString());
                    return;
                }
                return;
            }
            
            for(int i=0; i<=9; i++){
                if(!usedNumbers[i]){
                    usedNumbers[i] = !usedNumbers[i];
                    hm.put(unique.charAt(idx), i);
                    solution(unique, idx + 1, hm, usedNumbers, s1,s2,s3);
                    usedNumbers[i] = false;
                    hm.put(unique.charAt(idx), 0);
                }
            }
      }
}
