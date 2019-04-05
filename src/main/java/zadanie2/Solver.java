package zadanie2;

public abstract class Solver {

    int n;

    public Solver(int n) {
        this.n = n;
    }

    void solve(Method method) {
        switch (method) {
            case BACK_TRACKING:
                startBackTracking();
                break;
            case FORWARD_CHECKING:
                startForwardChecking();
                break;
            default:
                System.out.println("Bad method.");

        }
    }

    abstract void startForwardChecking();

    abstract void startBackTracking();

}
