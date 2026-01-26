package at.Technikum;

public class TestContext {
    public final MainApp app = new MainApp();

    public boolean chargingRejected = false;
    public Invoice lastInvoice = null;
    public NetworkOverview lastOverview = null;
    public boolean lastRejected = false;
}
