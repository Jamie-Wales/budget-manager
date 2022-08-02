package budget;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Sort {
    public static List<String> bubbleSort(List<String> lst) {
        int itr = lst.size();
        String[] output = new String[lst.size()];
        lst.toArray(output);

        for (int  i = 0; i < itr; i++) {
            for (int j = 0; j < (itr - i) - 1; j++)  {
                float check = Float.parseFloat(output[j].substring(output[j].lastIndexOf("$") + 1));
                float against = Float.parseFloat(output[j + 1].substring(output[j + 1].lastIndexOf("$") + 1));

                if (check < against) {
                    String temp = output[j + 1];
                    output[j + 1] = output[j];
                    output[j] = temp;
                }
            }
        }

        return Arrays.asList(output);

    }
}
