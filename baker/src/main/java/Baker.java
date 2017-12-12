import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
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
        if (drunkenness.chanceOfHearing() < 0.25) {
            sleepForever();
            return null;
        }
        else if (drunkenness.chanceOfHearing() >= 0.25 && drunkenness.chanceOfHearing() < 0.5) {
            return "OK";
        }
        else if (drunkenness.chanceOfHearing() >= 0.5 && drunkenness.chanceOfHearing() < 0.75) {
            prepareTheOrder(req);
            sleepForever();
            return null;
        } else {
            prepareTheOrder(req);
            return "OK";
        }
    }

    private void prepareTheOrder(Request req) {
        JSONObject order = new JSONObject(req.body());
        order.put("madeBy", "Barry");

        OkHttpClient httpClient = new OkHttpClient();
        RequestBody body = RequestBody.create(
                okhttp3.MediaType.parse("application/json"),
                order.toString()
        );

        okhttp3.Request bakerRequest = new okhttp3.Request.Builder()
                .url("http://shop_floor:5000/v1/receive-order-from-baker")
                .post(body)
                .build();

        try {
            httpClient.newCall(bakerRequest).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
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