import okhttp3.*;
import org.junit.After;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class BakerTest {

    private Baker baker;

    @After
    public void tearDown() throws Exception {
        baker.stop();
    }

    @Test
    public void drunk_baker_hears_order_and_returns_cake() throws Exception {
        baker = new Baker(this::alwaysHears);
        baker.start();

        Response response = placeOrderWithBaker();

        JSONAssert.assertEquals("{" +
                    "\"name\": \"Cake\"," +
                    "\"quantity\": 1," +
                    "\"madeBy\": \"Barry\"" +
                "}",
                response.body().string(), false);
    }

    @Test
    public void test_drunk_baker_does_not_hear_order_and_never_responds() throws Exception {
        baker = new Baker(this::neverHears);
        baker.start();

        Thread myThread = new Thread(() -> {
            try {
                placeOrderWithBaker();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        myThread.start();

        Thread.sleep(2000);

        assertThat(myThread.isAlive(), is(true));
    }

    private Response placeOrderWithBaker() throws IOException {
        OkHttpClient httpClient = new OkHttpClient();
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"),
                "{\"name\": \"Cake\", \"quantity\": 1}"
        );

        Request request = new Request.Builder()
                .url("http://localhost:4567/v1/order")
                .post(body)
                .build();

        return httpClient.newCall(request).execute();
    }

    private double alwaysHears() {
        return 1;
    }

    private double neverHears() {
        return 0;
    }
}