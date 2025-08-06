package communication;

import java.io.Serializable;
import java.util.List;

public class KWICMessage implements Serializable {
    private final List<String> message;
    private final KWICMethod method;

    public KWICMessage(List<String> message, KWICMethod method) {
        this.message = message;
        this.method = method;
    }


    public List<String> getMessage() {
        return this.message;
    }

    public KWICMethod getMethod() {
        return this.method;
    }
}
