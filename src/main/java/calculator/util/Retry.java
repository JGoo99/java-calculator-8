package calculator.util;

public final class Retry {
    private Retry() {}

    public static <T> T untilSuccess(SupplierWithEx<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @FunctionalInterface
    public interface SupplierWithEx<T> {
        T get();
    }
}
