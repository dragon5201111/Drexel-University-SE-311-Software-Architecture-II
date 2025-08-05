package communication;

public class KWICRequest {
    private final Method method;

    public KWICRequest(Method method) {
        this.method = method;
    }

    Method getMethod() {
        return this.method;
    }
}
