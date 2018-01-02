package io.github.nanodankster.skraper.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jsoup.nodes.Document;

/**
 * 2.01.2018 16:03
 */
@Name("Last received document")
@Description("Get the last document received from the document request effect.")
@Examples({"set {_document} to last received html document"})
@Since("1.0-BETA")
public class ExprReceivedDocument extends SimpleExpression<Document> {
    static {
        Skript.registerExpression(ExprReceivedDocument.class, Document.class, ExpressionType.SIMPLE,
                "[the] [last[ly]] [received] [html] doc[ument]");
    }

    @Override
    protected Document[] get(Event event) {
        return new Document[]{EffRequestDocument.lastHtmlDocument};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Document> getReturnType() {
        return Document.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "last received html document";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        return true;
    }
}
