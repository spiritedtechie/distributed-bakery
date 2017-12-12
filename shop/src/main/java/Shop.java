import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import org.json.JSONObject;
import spark.Response;

import java.io.IOException;

import static spark.Spark.port;
import static spark.Spark.post;

public class Shop {

    private static final String BAKER_ENDPOINT = "http://baker:4567/v1/order";

    public void start() {
        port(5000);
        post("/v1/order", this::makeAnOrder);
    }

    private Object makeAnOrder(spark.Request req, Response res) throws IOException {
        okhttp3.Response response = yellTheOrder(req);
        return sayWhatWasReceived(response);
    }

    private okhttp3.Response yellTheOrder(spark.Request request) throws IOException {
        OkHttpClient httpClient = new OkHttpClient();
        RequestBody body = RequestBody.create(
                okhttp3.MediaType.parse("application/json"),
                request.body()
        );

        okhttp3.Request bakerRequest = new okhttp3.Request.Builder()
                .url(BAKER_ENDPOINT)
                .post(body)
                .build();

        return httpClient.newCall(bakerRequest).execute();
    }

    private Object sayWhatWasReceived(okhttp3.Response response) throws IOException {
        JSONObject order = new JSONObject(response.body().string());
        return String.format("I received %s %s made by %s",
                order.get("quantity"),
                order.get("name"),
                order.get("madeBy"));
    }
}
