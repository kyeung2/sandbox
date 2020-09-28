package io.flyingnimbus.collections;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConcurrentMapRegisterTest {

    private final ConcurrentMapRegister objectUnderTest = new ConcurrentMapRegister();

    @Test()
    public void doesNotAllowNullKeys() {

        assertThrows(NullPointerException.class, () -> objectUnderTest.registerPerson(null, 999));
    }

    @Test()
    public void doesNotAllowNullValues() {

        assertThrows(NullPointerException.class, () -> objectUnderTest.registerPerson("Kye", null));
    }

    @Test
    public void writeOperationsFasterThanSynchronizedMap() throws InterruptedException {

        long concurrentMapDuration = timePutOperations(objectUnderTest);
        long synchronizedMapDuration = timePutOperations(new SynchronizedMapRegister());

        assertTrue(concurrentMapDuration < synchronizedMapDuration);
    }

    private long timePutOperations(Register register) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        AtomicLong duration = new AtomicLong(0);
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {

                for (int j = 0; j < 100_000; j++) {

                    String name = j + "Kye:" + j;
                    long start = System.currentTimeMillis();
                    register.registerPerson(name, 99);
                    long end = System.currentTimeMillis();
                    duration.addAndGet(end - start);
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);


        return duration.get();
    }

}