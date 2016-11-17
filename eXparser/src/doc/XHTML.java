/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doc;

import element.Tag;
import data.Attribute;
import node.Node;

/**
 *
 * @author Claudis
 */
public class XHTML extends Document {

    public XHTML() {

    }

    public void createHtml() {
        Tag tag = new Tag("html", getRoot());
        tag.add(new Attribute("xmlns", "http://www.w3.org/1999/xhtml"));
        tag.add(new Attribute("xmlns:h", "http://xmlns.jcp.org/jsf/html"));
        tag.add(new Attribute("xmlns:f", "http://java.sun.com/jsf/core"));
        tag.add(new Attribute("xmlns:ui", "http://java.sun.com/jsf/facelets"));
    }

    public Tag getHtml() {
        Tag tag = null;
        for (Node node : getRoot().getChildrens()) {
            if (node.getName().equals("html")) {
                tag = (Tag) node;
                break;
            }
        }
        return tag;
    }

    public Tag getHeader() {
        Tag tag = null;
        if (getHtml().getChildrens() != null) {
            for (Node node : getHtml().getChildrens()) {
                if (node.getName().equals("h:head")) {
                    tag = (Tag) node;
                    break;
                }
            }
        }
        if (tag == null) {
            tag = new Tag("h:head", getHtml());
        }
        return tag;
    }

    public Tag getBody() {
        Tag tag = null;
        if (getHtml().getChildrens() != null) {
            for (Node node : getHtml().getChildrens()) {
                if (node instanceof Tag) {
                    if (((Tag) node).getName().equals("h:body")) {
                        tag = (Tag) node;
                        break;
                    }
                }
            }
        }
        if (tag == null) {
            tag = new Tag("h:body", getHtml());
        }
        return tag;
    }

    public Tag getTagById(Tag begin, String id) {
        Tag result = null;
        if (begin != null) {
            if (begin.getChildrens() != null && begin.getChildrens().size() > 0) {
                for (Node node : begin.getChildrens()) {
                    if (node instanceof Tag) {
                        Tag tag = (Tag) node;
                        if (compareToId(tag, id)) {
                            result = tag;
                            break;
                        } else {
                            result = getTagById(tag, id);
                            if (result != null) {
                                break;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    public boolean compareToId(Tag tag, String id) {
        boolean result = false;
        if (tag != null) {
            if (tag.getAttributes() != null && tag.getAttributes().size() > 0) {
                Attribute att = tag.getAttributeByName("id");
                if (att != null) {
                    if (att.getValue().equals(id)) {
                        result = true;
                    }
                }
            }
        }
        return result;
    }

}
