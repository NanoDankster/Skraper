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
import org.jsoup.nodes.Element;

@Name("Attribute of element")
@Description("Get the specified attribute of an element.")
@Examples({"set {_attribute} to attribute \"abs:href\" of element {_element}"})
@Since("1.0-BETA")
public class ExprElementAttribute extends SimpleExpression<String> {
    static {
        Skript.registerExpression(ExprElementAttribute.class, String.class, ExpressionType.PROPERTY,
                "[the] attribute %string% of [[html] element] %htmlelement%");
    }

    private Expression<String> expr_str;
    private Expression<Element> expr_elm;

    @Override
    protected String[] get(Event event) {
        if (expr_elm == null || expr_str == null) {
            return null;
        } else {
            return new String[]{expr_elm.getSingle(event).attr(expr_str.getSingle(event))};
        }
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
        return "attribute " + expr_str.getSingle(event) + " of an element";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        expr_str = (Expression<String>) expressions[0];
        expr_elm = (Expression<Element>) expressions[1];
        return true;
    }
}
