package mastocli;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record Account(
        String username,
        Long id
) {
}
