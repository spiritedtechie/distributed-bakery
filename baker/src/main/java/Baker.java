import org.json.JSONObject;
import spark.Request;
import spark.Response;
import spark.Spark;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Random;

import static spark.Spark.*;

public class Baker {

    private final Drunkenness drunkenness;

    public static void main(String... args) {
        new Baker(() -> new Random().nextDouble()).start();
    }

    public Baker(Drunkenness drunkenness) {
        this.drunkenness = drunkenness;
    }

    public void start() {
        post("/v1/order", this::placeOrder);

        waitForServerToStart();
    }

    public void stop() {
        Spark.stop();

        waitForServerToStop();
    }

    public Object placeOrder(Request req, Response res) {
        if (drunkenness.chanceOfHearing() > 0.5) {
            return prepareTheOrder(req);
        } else {
            sleepForever();
            return null;
        }
    }

    private Object prepareTheOrder(Request req) {
        JSONObject json = new JSONObject(req.body());
        json.put("madeBy", "Barry");
        return json;
    }

    private void sleepForever() {
        try {
            Thread.sleep(1_000_000);
        } catch (InterruptedException ignore) {}
    }

    private boolean serverIsRunning() {
        try {
            new ServerSocket(4567).close();
            return false;
        } catch (IOException serverWasRunning) {
            return true;
        }
    }

    private void waitForServerToStart() {
        while (!serverIsRunning()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignore) {}
        }
    }

    private void waitForServerToStop() {
        while (serverIsRunning()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignore) {}
        }
    }

    interface Drunkenness {
        double chanceOfHearing();
    }
}