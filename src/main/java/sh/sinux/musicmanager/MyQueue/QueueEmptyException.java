package sh.sinux.musicmanager.MyQueue;

public class QueueEmptyException extends RuntimeException {
    public QueueEmptyException(){
        super("Queue is empty.");
    }

}