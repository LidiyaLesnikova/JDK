package DZ;

public class Fork {
    private volatile boolean status = false; //свободная

    public boolean getStatus() {
        return status;
    }

    public synchronized void setStatus(boolean status) {
        this.status = status;
    }

}
