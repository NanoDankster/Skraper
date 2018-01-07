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

@Name("Text of element")
@Description("Get the text of an element.")
@Examples({"set {_text} to html text of element {_element}"})
@Since("1.0")
public class ExprElementText extends SimpleExpression<String> {
    static {
        Skript.registerExpression(ExprElementText.class, String.class, ExpressionType.PROPERTY,
                "[the] [html] text of %htmlelement%");
    }

    private Expression<Element> expr_elm;

    @Override
    protected String[] get(Event event) {
        if (expr_elm == null) {
            return null;
        } else {
            return new String[]{expr_elm.getSingle(event).ownText()};
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
        return "text of an element";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        expr_elm = (Expression<Element>) expressions[0];
        return true;
    }
}
