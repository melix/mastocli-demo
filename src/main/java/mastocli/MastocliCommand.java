package mastocli;

import io.micronaut.configuration.picocli.PicocliRunner;
import io.micronaut.core.annotation.ReflectiveAccess;
import jakarta.inject.Inject;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "mastocli", description = "A demo Mastodon client using Micronaut",
        mixinStandardHelpOptions = true)
@ReflectiveAccess
public class MastocliCommand implements Runnable {
    @Inject
    private MastodonClient client;

    @Option(names = {"-u", "--user"}, description = "The username to retrieve statuses from")
    String username;

    @Option(names = {"-p", "--post"}, description = "Post a new status")
    String status;

    public static void main(String[] args) throws Exception {
        PicocliRunner.run(MastocliCommand.class, args);
    }

    public void run() {
        // business logic here
        if (status != null) {
            client.postStatus(status);
        } else {
            if (username != null) {
                client.lookupAccount(username).ifPresentOrElse(user -> {
                    var statuses = client.getStatuses(user.id());
                    for (Status status : statuses) {
                        String spoiler = status.spoilerText();
                        if (spoiler != null && !spoiler.isEmpty()) {
                            System.out.println("CW: " + spoiler);
                        }
                        System.out.println(Jsoup.clean(status.content(), new Safelist()));
                    }
                }, () -> System.out.println("User '" + username + "' not found on this server"));
            } else {
                System.out.println("Please provide the username to fetch statuses from, e.g `-u melix`");
            }
        }
    }
}
