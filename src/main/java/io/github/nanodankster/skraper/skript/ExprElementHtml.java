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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

@Name("Inner/outer html of element")
@Description("Get the inner or outer html of a htmlelement as a htmldocument.")
@Examples({"set {_inner} to inner html of element {_element}",
            "set {_outer} to outer html of element {_element}"})
@Since("1.2")
public class ExprElementHtml extends SimpleExpression<Document> {
    static {
        Skript.registerExpression(ExprElementHtml.class, Document.class, ExpressionType.PROPERTY,
                "(0¦inner|1¦outer) html (of|from) [element] %htmlelement%");
    }
    private Expression<Element> expr_elm;
    private Integer ParseMark;

    @Override
    protected Document[] get(Event event) {
        if (expr_elm == null) {
            return null;
        } else {
            switch (ParseMark) {
                case 0:
                    return new Document[]{Jsoup.parse(expr_elm.getSingle(event).html())};
                case 1:
                    return new Document[]{Jsoup.parse(expr_elm.getSingle(event).outerHtml())};
            }
            return null;
        }
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
        return "inner or outer html of an element";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        expr_elm = (Expression<Element>) expressions[0];
        ParseMark = parseResult.mark;
        return true;
    }
}
