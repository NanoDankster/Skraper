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


@Name("Title of document")
@Description("Get the html title of a document.")
@Examples({"set {_title} to html title of document {_document}"})
@Since("1.2")
public class ExprDocumentTitle extends SimpleExpression<String> {
    static {
        Skript.registerExpression(ExprDocumentTitle.class, String.class, ExpressionType.PROPERTY,
                "[html] title (of|from) %htmldocument%");
    }

    private Expression<Document> expr_doc;

    @Override
    protected String[] get(Event event) {
        return new String[]{expr_doc.getSingle(event).title()};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "title of a document";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        expr_doc = (Expression<Document>) expressions[0];
        return true;
    }
}
