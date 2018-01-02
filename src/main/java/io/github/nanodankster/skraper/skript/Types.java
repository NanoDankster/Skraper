package io.github.nanodankster.skraper.skript;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Types {
    static {
        Classes.registerClass(new ClassInfo<>(Element.class, "htmlelement")
                .user("htmlelement")
                .parser(new Parser<Element>() {

                    @Override
                    public Element parse(String s, ParseContext context) {
                        return null;
                    }

                    @Override
                    public boolean canParse(ParseContext context) {
                        return false;
                    }

                    @Override
                    public String toString(Element element, int i) {
                        return element.toString();
                    }

                    @Override
                    public String toVariableNameString(Element element) {
                        return element.toString();
                    }

                    @Override
                    public String getVariableNamePattern() {
                        return ".+";
                    }
                })

        );

        Classes.registerClass(new ClassInfo<>(Document.class, "htmldocument")
                .user("htmldocument")
                .parser(new Parser<Document>() {

                    @Override
                    public Document parse(String s, ParseContext context) {
                        return null;
                    }

                    @Override
                    public boolean canParse(ParseContext context) {
                        return false;
                    }

                    @Override
                    public String toString(Document document, int i) {
                        return document.toString();
                    }

                    @Override
                    public String toVariableNameString(Document document) {
                        return document.toString();
                    }

                    @Override
                    public String getVariableNamePattern() {
                        return ".+";
                    }
                })

        );
    }
}
