package lection2.tictak;

public class Main {

    Worker[] workers = new Worker[2];

    public static void main(String[] args) throws InterruptedException {
        TicTak();
        //TicTakToe();
    }

    private static void TicTakToe() throws InterruptedException {
        Data data = new Data();
        Object sync1 = new Object();
        Object sync2 = new Object();
        Object sync3 = new Object();

        Worker workerOne = new Worker(1, sync1, sync2, data);
        Worker workerTwo = new Worker(2, sync2, sync3, data);
        Worker workerThree = new Worker(3, sync3, sync1, data);

        workerThree.signalToNext();

        workerOne.join();
        workerTwo.join();
        workerThree.join();
        System.out.println("end of main...");
    }

    private static void TicTak() throws InterruptedException {
        Data data = new Data();
        Object sync1 = new Object();
        Object sync2 = new Object();

        Worker workerOne = new Worker(1, sync1, sync2, data);
        Worker workerTwo = new Worker(2, sync2, sync1, data);

        workerTwo.signalToNext();

        workerOne.join();
        workerTwo.join();
        System.out.println("end of main...");
    }
}
