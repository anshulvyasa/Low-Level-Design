package org.lld.rate_limiter;

import org.lld.rate_limiter.enums.UserType;
import org.lld.rate_limiter.models.User;
import org.lld.rate_limiter.service.RateLimiterService;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws  Exception {
        User freeUser=new User(1, UserType.FREE);
        User premiumUser=new User(2,UserType.PREMIUM);
        RateLimiterService rateLimiterService=new RateLimiterService();

        int threads=20;
        ExecutorService threadPool= Executors.newFixedThreadPool(threads);

        CyclicBarrier barrier=new CyclicBarrier(threads);
        CountDownLatch latch=new CountDownLatch(threads);

        for(int i=0;i<20;i++){
            final int reqNum=i;

            threadPool.submit(() -> {
                try {
                    // Threads wait here
                    barrier.await();

                    // This must be inside the try-catch if it throws a checked exception
                    boolean allowed = rateLimiterService.allowUser(freeUser);
                    System.out.println(Thread.currentThread().getName() + " | request " + reqNum + " for FreeUser: " + (allowed ? "Allowed" : "Blocked"));

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // Always ensure the latch counts down even if an exception occurs
                    latch.countDown();
                }
            });
        }

        latch.await();
        threadPool.shutdown();
    }
}
