@FunctionalInterface
public interface JsonFactory {
    Storable fromJson(String json);
}
