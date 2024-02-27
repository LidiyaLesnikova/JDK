package DZ;

public class Philosopher {
    private StatusPhilosopher statusPhilosopher;

    public Philosopher() {
        this.statusPhilosopher = StatusPhilosopher.thinks;
    }
    public StatusPhilosopher getStatusPhilosopher() {
        return statusPhilosopher;
    }
    public StatusPhilosopher toEat(Fork one, Fork two) {
        if (!one.getStatus() && !two.getStatus()) {
            one.setStatus(true);
            two.setStatus(true);
            this.statusPhilosopher = StatusPhilosopher.eats;
        }
        return statusPhilosopher;
    }
    public void toThinks(Fork one, Fork two) {
        one.setStatus(false);
        two.setStatus(false);
        this.statusPhilosopher = StatusPhilosopher.thinks;
    }
}
