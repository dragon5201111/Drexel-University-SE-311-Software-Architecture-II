package communication;

import java.util.List;

public class KWICResponse {
    private final List<String> message;

    public KWICResponse(List<String> message) {
        this.message = message;
    }

    public List<String> getMessage() {
        return this.message;
    }
}
