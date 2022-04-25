import java.util.*;
import java.util.ArrayList;

public class String {
     public static ArrayList<String> sub_sequence(String str){
    ArrayList<String> ans = new ArrayList<>();
          ans.add("");

    for (int i = 0; i < str.length(); i++) {
        char ch = str.charAt(i);
        int size = ans.size();
        for (int j = 0; j < size; j++) {
            ans.add((ans.get(j) + ch));
        }
    }

    return ans;
}


    public static void main( String[]args){




    }
    
}
