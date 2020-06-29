public class CalcTask implements Runnable {
    private int sizeArray;

    public CalcTask(int sizeArray) {
        this.sizeArray = sizeArray;
    }

    @Override
    public void run() {
        float[] arr = new float[sizeArray];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1.0f;
        }
        long a = System.currentTimeMillis();
        System.out.println("Время начала расчета значений массива в потоках: " + a);
        int h = sizeArray / 2;
        float[] a1 = new float[h];
        float[] a2 = new float[h];
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
        Thread tA1 = new Thread(() -> {
            for (int i = 0; i < a1.length; i++) {
                a1[i] = (float) (a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5)
                        * Math.cos(0.4f + i / 2));
            }
        });
        tA1.start();

        Thread tA2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < a2.length; i++) {
                    a2[i] = (float) (a2[i] * Math.sin(0.2f + h / 5) * Math.cos(0.2f + h / 5)
                            * Math.cos(0.4f + h / 2));
                }
            }
        });
        tA2.start();
        if (tA1.isAlive() || tA2.isAlive()) {
            try {
                tA1.join();
                tA2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Проверяем время окончания метода с потоками: " + System.currentTimeMillis());
        System.out.println("Расчет значений массива в потоках длилось: " + (System.currentTimeMillis() - a));


    }

    public void calcValuesArrayWithoutThread() {
        float[] arr = new float[sizeArray];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1.0f;
        }
        long a = System.currentTimeMillis();
        System.out.println("Время начала расчета значений массива не в потоках: " + a);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5)
                    * Math.cos(0.4f + i / 2));
        }
        System.out.println("Проверяем время окончания метода: " + System.currentTimeMillis());
        System.out.println("Расчет значений массива не в потоках длилось: " + (System.currentTimeMillis() - a));
    }

    public void calcValuesArrayWithThread() throws InterruptedException {
        Thread thread = new Thread(new CalcTask(10_000_000));
        thread.start();
        thread.join();

    }
}
