package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Server {
    private ExecutorService executorService;

    public Server() {
        this.executorService = Executors.newFixedThreadPool(64);
    }

    public <T> T addHandler(HttpMethod method, String path, Runnable task) {
        Future future = executorService.submit(task);
        try {
            return (T) future.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
