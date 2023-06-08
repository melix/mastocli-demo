package mastocli;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

@MicronautTest
public class MastocliCommandTest {

    @Inject
    private MastodonClient client;

    @Test
    public void testCanReadStatus() throws Exception {
        assert client.lookupAccount("__nobody__").isEmpty();
        var melix = client.lookupAccount("melix").get();
        var statuses = client.getStatuses(melix.id());
        assert statuses.size() > 0;
        for (Status status : statuses) {
            System.out.println(status);
        }
    }
}
