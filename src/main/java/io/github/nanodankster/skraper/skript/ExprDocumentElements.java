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
import org.jsoup.select.Elements;

@Name("Elements from document")
@Description("Get the specified elements of a document.")
@Examples({"set {_elements::*} to elements \"a[href]\" of document {_document}"})
@Since("1.0-BETA")
public class ExprDocumentElements extends SimpleExpression<Element> {
    static {
        Skript.registerExpression(ExprDocumentElements.class, Element.class,
                ExpressionType.PROPERTY,
                "[the] element[s] %string% (of|from) [[html] doc[ument]] %htmldocument%");
    }

    private Expression<String> expr_str;
    private Expression<Document> expr_doc;

    @Override
    protected Element[] get(Event event) {
        if (expr_doc.getSingle(event) != null) {
            Elements elms = expr_doc.getSingle(event).select(expr_str.getSingle(event));
            Element[] ret = new Element[elms.size()];
            int i = 0;
            for (Element e : elms) {
                ret[i] = e;
                i++;
            }
            return ret;
        }
        return null;
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Element> getReturnType() {
        return Element.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "elements " + expr_str.getSingle(event) + " of a document";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        expr_str = (Expression<String>) expressions[0];
        expr_doc = (Expression<Document>) expressions[1];
        return true;
    }
}
