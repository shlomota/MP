import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class QuickSortTask extends RecursiveAction {
    private Long[] arr;
    private int start, end;

    public QuickSortTask(Long[] pArr, int pStart, int pEnd) {
        arr = pArr;
        start = pStart;
        end = pEnd;
    }

    @Override
    protected void compute() {
        int partition = partition(arr, start, end);

        if(partition-1>start) {
            QuickSortTask left = new QuickSortTask(arr, start, partition - 1);
            left.compute();
        }
        if(partition+1<end) {
            QuickSortTask right = new QuickSortTask(arr, partition + 1, end);
            right.compute();
        }
    }

    public static int partition(Long[] arr, int start, int end){
        Long pivot = arr[end];

        for(int i=start; i<end; i++){
            if(arr[i]<pivot){
                Long temp= arr[start];
                arr[start]=arr[i];
                arr[i]=temp;
                start++;
            }
        }

        Long temp = arr[start];
        arr[start] = pivot;
        arr[end] = temp;

        return start;
    }
}