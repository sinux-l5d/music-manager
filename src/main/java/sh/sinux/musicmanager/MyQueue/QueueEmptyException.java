package sh.sinux.musicmanager.MyQueue;

public class QueueEmptyException extends RuntimeException {
    public QueueEmptyException(String message){
        super(message);
    }

    public QueueEmptyException()
    {
    }
}