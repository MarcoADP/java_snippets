package org.example.lock;

public class ReadWriteLock {

    private int readLock = 0;
    private int writeLock = 0;
    private int writeRequests = 0;

    public synchronized void addReadLock(String value) throws InterruptedException {
        while (writeLock > 0 ||  writeRequests > 0){
            System.out.println(value + " is waiting to read...");
            wait();
        }
        readLock++;
    }

    public synchronized void unlockRead(){
        readLock--;
        notifyAll();
    }

    public synchronized void addWriteLock(String value) throws InterruptedException {
        writeRequests++;
        while (readLock > 0 || writeLock > 0){
            System.out.println(value + " is waiting to write...");
            wait();
        }
        writeRequests--;
        writeLock++;
    }

    public synchronized void unlockWriteLock(){
        writeLock--;
        notifyAll();
    }
}
