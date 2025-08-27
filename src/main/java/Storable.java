public interface Storable {
    String toJson();
    default String getType() {
        return this.getClass().getSimpleName();
    };
}
