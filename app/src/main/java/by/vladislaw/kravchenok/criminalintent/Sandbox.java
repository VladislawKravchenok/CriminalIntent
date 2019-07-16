package by.vladislaw.kravchenok.criminalintent;

/**
 * Created by Vladislaw Kravchenok on 10.07.2019.
 */
public final class Sandbox {
    private static  Integer count;

    public static void main(String[] args) {
        final Sandbox sandbox = new Sandbox();
        count = 0;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count < 50000) {
                    sandbox.increment();
                    System.out.println("1" + ":" + count);

                }
                System.out.println("CCCCCCCCCCCCC" + count);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count < 50000) {
                    sandbox.increment();
                    System.out.println("2" + ":" + count);
                }
                System.out.println("BBBBBBBBBBBB" + count);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count < 50000) {

                    sandbox.increment();
                    System.out.println("3" + ":" + count);
                }
                System.out.println("AAAAAAAAAAAA" + count);
            }
        }).start();
        System.out.println(Math.abs(-90));
        ;
    }

    public  void increment() {
        synchronized (count){
            count++;
        }


    }

}
