import Engine.Engine;

public class Loop {
    private static boolean running = false;

    private static  int FPS = 60;
    private static int second_per_frame = 1000000000/FPS;

    public static void start() {

        Thread thread = new Thread(() -> {
            running = true;
            while (running) {
                long start_time = System.nanoTime();

                Engine.update();


                long taken_time = System.nanoTime() - start_time;

                // максимальный возможный фпс в консоль
                //System.out.println(1000000000/(System.nanoTime() - start_time));

                if (taken_time < second_per_frame) {
                    try {
                        Thread.sleep((second_per_frame - taken_time) / 1000000);

                    } catch (InterruptedException ignored) {}
                }
            }
        });
        thread.setName("MapLoop");
        thread.start();
    }
}
