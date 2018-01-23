import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static spark.Spark.*;

public class ShopFloor {

    static final String BAKER_ENDPOINT = "http://baker:4567/v1/order";

    private final HungryPerson hungryPerson;
    private final ShopAssistant shopAssistant;

    public static void main(String... args) {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        ShopAssistant shopAssistant = new ShopAssistant(queue);
        HungryPerson hungryPerson = new HungryPerson(queue, shopAssistant);

        new ShopFloor(hungryPerson, shopAssistant).start();
    }

    public ShopFloor(HungryPerson hungryPerson, ShopAssistant shopAssistant) {
        this.hungryPerson = hungryPerson;
        this.shopAssistant = shopAssistant;
    }

    private void start() {
        port(5000);
        post("/v1/order", hungryPerson::makeAnOrder);
        get("/v1/show-me-what-you-got", hungryPerson::showMeWhatYouGot);
        post("/v1/receive-order-from-baker", shopAssistant::receiveOrderFromBaker);
    }
}
