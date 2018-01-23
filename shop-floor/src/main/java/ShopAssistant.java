import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

class ShopAssistant {

    private final BlockingQueue<String> queue;

    ShopAssistant(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    // THIS NEEDS TO HANDLE DEALING WITH THE DRUNK BAKER AND HIS LACK OF ATTENTION
    String iWant(String order) throws IOException {
        new Thread(() -> {
            OkHttpClient httpClient = new OkHttpClient();
            RequestBody body = RequestBody.create(
                    okhttp3.MediaType.parse("application/json"),
                    order
            );

            okhttp3.Request bakerRequest = new okhttp3.Request.Builder()
                    .url(ShopFloor.BAKER_ENDPOINT)
                    .post(body)
                    .build();

            try {
                httpClient.newCall(bakerRequest).execute();
            } catch (IOException e) {
                e.printStackTrace();
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
