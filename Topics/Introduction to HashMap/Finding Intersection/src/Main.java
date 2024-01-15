import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

class Main {
    private static void printCommon(int[] firstArray, int[] secondArray) {
        // Enter your Code Here
       Map<Integer, Integer> hashMap = new TreeMap<>();
        for (int i = 0; i < firstArray.length; i++) {
            hashMap.merge(firstArray[i], 1, Integer::sum);
            hashMap.merge(secondArray[i], 1, Integer::sum);
        }
        for(var entry : hashMap.entrySet()) {
            if (entry.getValue() > 1) {
                System.out.printf("%d ", entry.getKey() );
            }
        }

    }

    public static void main(String[] args) {        
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] firstArray = new int [n];
        int[] secondArray = new int [n];
        for (int i = 0; i < n; ++i) { 
            firstArray[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; ++i) { 
            secondArray[i] = scanner.nextInt();
        }

        printCommon(firstArray,secondArray);
    }
}