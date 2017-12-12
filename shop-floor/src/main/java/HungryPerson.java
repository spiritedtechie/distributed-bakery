import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.util.concurrent.BlockingQueue;

public class HungryPerson {

    private final BlockingQueue<String> queue;
    private final ShopAssistant shopAssistant;

    public HungryPerson(BlockingQueue<String> queue, ShopAssistant shopAssistant) {
        this.queue = queue;
        this.shopAssistant = shopAssistant;
    }

    public String makeAnOrder(spark.Request req, Response res) throws Exception {
        return shopAssistant.iWant(req.body());
    }

    public String showMeWhatYouGot(Request request, Response response) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();

        while (!queue.isEmpty()) {
            stringBuilder.append(sayWhatWasReceived(queue.take()));
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    private String sayWhatWasReceived(String response) {
        JSONObject order = new JSONObject(response);
        return String.format("I received %s %s made by %s",
                order.get("quantity"),
                order.get("name"),
                order.get("madeBy"));
    }
}
