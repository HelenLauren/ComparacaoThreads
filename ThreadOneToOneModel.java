public class ThreadOneToOneModel {
    public static void main(String[] args) throws InterruptedException {
        //qtd das threads
        int[] realThreadCounts = {10, 100, 500, 1000};
        System.out.println("\n--- Modelo 1:1 ---");
        for (int n : realThreadCounts) {
            System.out.println("Executando " + n + " threads...");

            long inicio = System.currentTimeMillis();

            Thread[] threads = new Thread[n];

            //cria e inicia as N threads nativas
            for (int i = 1; i <= n; i++) {
                final int threadId = i;
                threads[i - 1] = new Thread(() -> {
                    for (int j = 0; j < 5; j++) {
                        try {
                            Thread.sleep(100); //
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                });
                threads[i - 1].start();
            }

            //aguarda terminar
            for (Thread t : threads) {
                t.join();
            }

            long fim = System.currentTimeMillis();
            System.out.println("Tempo total com " + n + " threads: " + (fim - inicio) + " ms");
        }
    }
}
