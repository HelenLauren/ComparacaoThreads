import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class NMThreadModel {
    public static void main(String[] args) throws InterruptedException {
        //qtd tarefas N
        int[] threadCounts = {100, 10, 20, 200};
        //núm fixo threads nativas M
        int systemThreads = 8;
        System.out.println("\n--- Modelo N:M ---");
        for (int n : threadCounts) {
            System.out.println("Executando " + n + " tarefas em " + systemThreads);

            long inicio = System.currentTimeMillis();

            //pool fixo das threads M (threads nativas)
            ExecutorService pool = Executors.newFixedThreadPool(systemThreads);

            //vai distribuir as N tarefas entre as M threads
            for (int i = 1; i <= n; i++) {
                final int threadId = i;
                pool.submit(() -> {
                    for (int j = 0; j < 5; j++) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                });
            }

            pool.shutdown();
            pool.awaitTermination(5, TimeUnit.MINUTES);
            // ^^^ isso daqui é so para se nao terminar de excutar as threads em 5 min

            long fim = System.currentTimeMillis();
            System.out.println("Tempo total com " + n + " tarefas: " + (fim - inicio) + " ms");
        }
    }
}
