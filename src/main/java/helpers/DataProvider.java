package helpers;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class DataProvider {
    public static Stream<Arguments> providerParameters() {
        return Stream.of(
                Arguments.of("Alexey Fadeev", "Fadeevae87@gmail.com", "Moscow, Leninskyi prospect", "Kazan, Baumana street")
        );
    }
}
