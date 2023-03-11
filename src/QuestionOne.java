public class QuestionOne {
    private static boolean ready = false;
    private static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReaderThread thread = new ReaderThread();
        thread.start();
        number = 66;
        ready = true;
        thread.join();
    }
}
