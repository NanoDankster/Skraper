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

@Name("Elements from document or element")
@Description("Get the specified elements of a document or an element.")
@Examples({"set {_elements::*} to elements \"a[href]\" of document {_document}",
            "set {_elements::*} to elements \"a[href]\" of element {_element}"})
@Since("1.0-BETA")
public class ExprDocElmElements extends SimpleExpression<Element> {
    static {
        Skript.registerExpression(ExprDocElmElements.class, Element.class,
                ExpressionType.PROPERTY,
                "[the] element[s] %string% (of|from) (0¦[[html] doc[ument]] %-htmldocument%|1¦[[html] element] %-htmlelement%)");
    }

    private Expression<String> expr_str;
    private Expression<?> expr_ty;
    private Integer ty;

    @Override
    protected Element[] get(Event event) {
        if (ty == 0) {
            if (expr_ty.getSingle(event) != null) {
                Expression<Document> doc = (Expression<Document>) expr_ty;
                Elements elms = doc.getSingle(event).select(expr_str.getSingle(event));
                Element[] ret = new Element[elms.size()];
                int i = 0;
                for (Element e : elms) {
                    ret[i] = e;
                    i++;
                }
                return ret;
            }
        } else if (ty == 1) {
            if (expr_ty.getSingle(event) != null) {
                Expression<Element> elm = (Expression<Element>) expr_ty;
                Elements elms = elm.getSingle(event).select(expr_str.getSingle(event));
                Element[] ret = new Element[elms.size()];
                int i = 0;
                for (Element e : elms) {
                    ret[i] = e;
                    i++;
                }
                return ret;
            }
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
        ty = parseResult.mark;
        expr_ty = expressions[ty + 1];
        return true;
    }
}
