import java.util.concurrent.CountDownLatch;

/**
 * 代码重排示例
 * 单例模式的DCL，当对象被分配空间后并且将内存的地址返回给对象后，其它线程再对实例做非空判断时已经不成立
 */
public class Main {
    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        for (long i = 0; i < Long.MAX_VALUE; i++) {
            x = 0;
            y = 0;
            a = 0;
            b = 0;
            CountDownLatch latch = new CountDownLatch(2);
            Thread thread = new Thread(() -> {
                a = 1;
                x = b;
                latch.countDown();
            });
            Thread thread1 = new Thread(() -> {
                b = 1;
                y = a;
                latch.countDown();
            });
            thread.start();
            thread1.start();
            latch.await();
            if (x == 0 && y == 0) {
                String result = "第" + i + "次";
                System.out.printf(result);
                break;
            }
        }
    }
}