package mastocli;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.serde.annotation.Serdeable;

import java.net.URI;
import java.time.ZonedDateTime;

@Serdeable
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record Status(
        @Nullable Long id,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        @Nullable ZonedDateTime createdAt,
        @Nullable Long inReplyToAccountId,
        boolean sensitive,
        @Nullable String spoilerText,
        @Nullable String visibility,
        @Nullable String language,
        URI uri,
        int repliesCount,
        int reblogsCount,
        int favouritesCount,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        @Nullable ZonedDateTime editedAt,
        String content
) {
}
