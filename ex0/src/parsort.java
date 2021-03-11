import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Arrays;
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList; // import the ArrayList class
import java.util.concurrent.ForkJoinPool;


public class parsort {
    private static ArrayList<Long> readIntegerFile(String fileName) {
        ArrayList<Long> nums = new ArrayList<>(); // Create an ArrayList object
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                nums.add(Long.parseLong(data));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return nums;
    }
    public static void main(String[] args) throws InterruptedException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Expected 2 arguments");
        }
        ArrayList<Long> nums = new ArrayList<Long>(); // Create an ArrayList object
        nums = readIntegerFile(args[0]);
        

        Long[] numsArr = new Long[nums.size()];
        nums.toArray(numsArr);
		
        ForkJoinPool forkJoinPool = new ForkJoinPool(Integer.parseInt(args[1]));

        long time = System.nanoTime();
        forkJoinPool.invoke(new QuickSortTask(numsArr, 0, numsArr.length - 1));
        System.out.println(String.format("Quicksort %d", (System.nanoTime() - time) / 1000));

        for (Long num : numsArr) {
            System.out.println(num);
        }
    }
}
