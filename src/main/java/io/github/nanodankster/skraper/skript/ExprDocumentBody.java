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
import org.jsoup.nodes.Element;

@Name("Body of document")
@Description("Get the html body of a document as an element.")
@Examples({"set {_body} to html body of document {_document}"})
@Since("1.2")
public class ExprDocumentBody extends SimpleExpression<Element> {
    static {
        Skript.registerExpression(ExprDocumentBody.class, Element.class, ExpressionType.PROPERTY,
                "[html] body (of|from) [document] %htmldocument%");
    }

    private Expression<Document> expr_doc;

    @Override
    protected Element[] get(Event event) {
        return new Element[]{expr_doc.getSingle(event).body()};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Element> getReturnType() {
        return Element.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "html body of a document";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        expr_doc = (Expression<Document>) expressions[0];
        return true;
    }
}
