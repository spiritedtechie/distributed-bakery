import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

class ShopAssistant {

    private final BlockingQueue<String> queue;

    ShopAssistant(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    // THIS NEEDS TO HANDLE DEALING WITH THE DRUNK BAKER AND HIS LACK OF ATTENTION
    String iWant(String order) throws IOException {
        new Thread(() -> {
            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .readTimeout(1, TimeUnit.SECONDS)
                    .connectTimeout(1, TimeUnit.SECONDS).build();

            RequestBody body = RequestBody.create(
                    okhttp3.MediaType.parse("application/json"),
                    order
            );

            okhttp3.Request bakerRequest = new okhttp3.Request.Builder()
                    .url(ShopFloor.BAKER_ENDPOINT)
                    .post(body)
                    .build();

            try {
                Response response = httpClient.newCall(bakerRequest).execute();

                while (!"OK".equals(response.body().string())) {
                    response = httpClient.newCall(bakerRequest).execute();
                }

                System.out.println("Received OK from Baker! for order: " + order);

            } catch (IOException e) {
            }
        }).start();

        return "OK";
    }

    // THIS NEEDS TO HANDLE DUPLICATE OR MISSING ORDERS FROM THE BAKER
    Object receiveOrderFromBaker(spark.Request request, spark.Response response) throws Exception {
        queue.put(request.body());

        return "OK";
    }
}
