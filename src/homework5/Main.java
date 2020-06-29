public class Main {

    public static void main(String[] args) throws InterruptedException {
        CalcTask ct = new CalcTask(10_000_000);
        ct.calcValuesArrayWithoutThread();
        CalcTask ctWithThread = new CalcTask(10_000_000);
        ctWithThread.calcValuesArrayWithThread();

    }
}
