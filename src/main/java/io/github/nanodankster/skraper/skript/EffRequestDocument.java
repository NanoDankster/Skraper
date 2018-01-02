package io.github.nanodankster.skraper.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.TriggerItem;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Name("Request document")
@Description("Request a document.")
@Examples({"request document from url \"http://www.google.com\""})
@Since("1.0-BETA")
public class EffRequestDocument extends Effect {
    static {
        Skript.registerEffect(EffRequestDocument.class, "request [html] doc[ument] (of|from) [url] %string%");
    }

    static Document lastHtmlDocument;

    private static final ExecutorService fixedThreadPool =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private Expression<String> expr_url;

    protected void execute(Event event) {
        CompletableFuture<Document> request =
                CompletableFuture.supplyAsync(() -> sendRequest(event), fixedThreadPool);

        request.whenComplete((resp, err) -> {
            if (err != null) {
                err.printStackTrace();
            }
            if (resp != null) {
                lastHtmlDocument = resp;
            }
            if (getNext() != null) {
                TriggerItem.walk(getNext(), event);
            }
        });
    }

    public String toString(Event event, boolean b) {
        return "send html document request";
    }

    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        expr_url = (Expression<String>) expressions[0];
        return true;
    }

    private Document sendRequest(Event e) {
        String url = expr_url.getSingle(e);
        if (url == null) {
            return null;
        }
        Connection conn = Jsoup.connect(url);
        try {
            return conn.get();
        } catch (IOException e1) {
            e1.printStackTrace();
            return null;
        }
    }

    protected TriggerItem walk(Event e) {
        debug(e, true);
        execute(e);
        return null;
    }
}
