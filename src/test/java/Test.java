import java.util.Random;
import java.util.*;

public class Test {
    public static void main(String[] args) {
        BinomenalHeap<Integer> heap = new BinomenalHeap<>();
        System.out.println("start");
        int[] data = new int[10000];
        Random r = new Random();
        for(int i = 0;i<10000;++i) {
            data[i] = r.nextInt();
        }
        long start,finish;
        for(int i:data){
            start = System.nanoTime();
            heap.add(i);
            finish = System.nanoTime();
            System.out.println(finish-start);
        }
        for(int i = 0,j;i<100;++i){
            j = r.nextInt(10001);
            start = System.nanoTime();
            heap.has(j);
            finish = System.nanoTime();
            System.out.println(finish-start);
        }
        for(int i = 0,j;i<1000;++i){
            j = r.nextInt(10001);
            start = System.nanoTime();
            heap.delete(j);
            finish = System.nanoTime();
            System.out.println(finish-start);
        }
        System.out.println("finish");
    }
}
