package sh.sinux.musicmanager.MyQueue;

/**
 * @author Zi Jie Lee
 */
public class QueueEmptyException extends RuntimeException {
    public QueueEmptyException(){
        super("Queue is empty.");
    }

}