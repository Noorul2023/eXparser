/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package element;

import java.util.ArrayList;
import java.util.List;
import data.Attribute;
import node.Node;
import node.Root;

/**
 *
 * @author Lucas
 */
public class Tag extends Node {

    private List<Attribute> attributes;

    private List<Node> childrens;

    public Tag(String name, String content, Node parent) {
        if (name != null) {
            setName(name);
        }
        if (content != null) {
            setContent(content);
            parse(content);
        }
    }

    public Tag(String name, Node parent) {
        setName(name);
        setParent(parent);
        if (parent instanceof Root) {
            ((Root) parent).add(this);
        } else if (parent instanceof Tag) {
            ((Tag) parent).add(this);
        }
    }

    public void setId(String id) {
        Attribute att = new Attribute("id", id);
        if (attributes == null) {
            attributes = new ArrayList<>();
        }
        attributes.add(att);
    }

    public void addClass(String _class) {
        boolean has = false;
        for (Attribute att : attributes) {
            if (att.getName().equals("class")) {
                att.setValue(_class);
                has = true;
                break;
            }
        }
        if (!has) {
            Attribute att = new Attribute("class", _class);
            attributes.add(att);
        }
    }

    public void setValue(String value) {
        Attribute att = new Attribute("value", value);
        if (attributes == null) {
            attributes = new ArrayList<>();
        }
        attributes.add(att);
    }

    public Attribute getAttributeByName(String name) {
        Attribute attribute = null;
        for (Attribute att : attributes) {
            if (att.getName().equals(name)) {
                attribute = att;
                break;
            }
        }
        return attribute;
    }

    public void updateValueToAttribute(String name, String value) {
        for (Attribute att : attributes) {
            if (att.getName().equals(name)) {
                att.setValue(value);
                break;
            }
        }
    }

    public void removeAttribute(String name) {
        int index = 0;
        for (Attribute att : attributes) {
            if (att.getName().equals(name)) {
                break;
            }
            index++;
        }
        attributes.remove(index);
    }

    public void addValueToAttribute(String name, String value) {
        Attribute attribute = null;
        if (attributes != null) {
            for (Attribute att : attributes) {
                if (att.getName().equals(name)) {
                    attribute = att;
                    break;
                }
            }
        }
        if (attribute == null) {
            attribute = new Attribute(name, value);
            add(attribute);
        } else {
            attribute.setValue(attribute.getValue() + " " + value);
        }
    }

    public void add(Attribute att) {
        if (attributes == null) {
            attributes = new ArrayList<>();
        }
        attributes.add(att);
    }

    public void add(Tag tag) {
        if (childrens == null) {
            childrens = new ArrayList<>();
        }
        tag.setParent(this);
        childrens.add(tag);
    }

    public void add(Element element) {
        if (childrens == null) {
            childrens = new ArrayList<>();
        }
        element.setParent(this);
        childrens.add(element);
    }

    public void add(Comment comment) {
        if (childrens == null) {
            childrens = new ArrayList<>();
        }
        comment.setParent(this);
        childrens.add(comment);
    }

    public Tag getTagByAttribute(Tag begin, Attribute attribute) {
        Tag result = null;
        if (begin.getChildrens() != null && begin.getChildrens().size() > 0) {
            for (Node node : begin.getChildrens()) {
                if (node instanceof Tag) {
                    Tag t = (Tag) node;
                    if (t.getAttributes() != null && t.getAttributes().size() > 0) {
                        for (Attribute att : t.getAttributes()) {
                            if (att.getName().equals(attribute.getName())) {
                                if (att.getValue().equals(attribute.getValue())) {
                                    result = t;
                                    break;
                                }
                            }
                        }
                    } else if (t.getChildrens() != null && t.getChildrens().size() > 0) {
                        result = getTagByAttribute(t, attribute);
                    }
                    if (result != null) {
                        break;
                    }
                }
            }
        }
        return result;
    }

    public void parse(String content) {
        char[] chars = content.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            //get name
            if (chars[i] == '<') {
                String name = "";
                for (int h = i + 1; h < chars.length; h++) {
                    if (chars[h] == ' ' || chars[h] == '/' || chars[h] == '>') {
                        i = h;
                        setName(name);
                        if (chars[h] != '>') {
                            setAttributes(new ArrayList<Attribute>());
                        }
                        break;
                    } else {
                        name += chars[h];
                    }
                }
            }

            if (Character.isWhitespace(chars[i]) && Character.isAlphabetic(chars[i + 1])) {

                Attribute att = new Attribute();
                String att_name = "";
                String value = "";

                for (int h = i + 1; h < chars.length; h++) {

                    if (chars[h] == '=') {
                        i = h;
                        att.setName(att_name);
                        break;
                    } else {
                        att_name += chars[h];
                    }

                }

                for (int h = i + 2; h < chars.length; h++) {

                    if (chars[h] == '"' || chars[h] == '\'') {
                        i = h;
                        att.setValue(value);
                        break;
                    } else {
                        value += chars[h];
                    }
                }
                this.getAttributes().add(att);
            }

        }
    }

    public List<Node> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<Node> childrens) {
        this.childrens = childrens;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getContent() {
        String content = "";
        if (attributes != null && attributes.size() > 0) {
            content += "\n" + getColumnSpaces() + "<" + getName();
            if (attributes.size() < 3) {
                for (int i = 0; i < attributes.size(); i++) {
                    content += " " + attributes.get(i).getName() + "=\"" + attributes.get(i).getValue() + "\"";
                }
            } else {
                String space = getColumnSpaces() + " ";
                for (int i = 0; i < getName().length(); i++) {
                    space += " ";
                }
                content += " " + attributes.get(0).getName() + "=\"" + attributes.get(0).getValue() + "\"";
                for (int i = 1; i < attributes.size(); i++) {
                    content += "\n" + space + " " + attributes.get(i).getName() + "=\"" + attributes.get(i).getValue() + "\"";
                }
            }
        } else {
            content += "\n" + getColumnSpaces() + "<" + getName();
        }
        if (childrens != null && childrens.size() > 0) {
            content += ">";
            for (int i = 0; i < childrens.size(); i++) {
                content += childrens.get(i).getContent();
            }
            if (childrens.get(childrens.size() - 1) instanceof Element) {
                content += "" + "</" + getName() + ">";
            } else if (childrens.get(childrens.size() - 1) instanceof Comment) {
                content += "\n" + getColumnSpaces() + "</" + getName() + ">";
            } else {
                content += "\n" + getColumnSpaces() + "</" + getName() + ">";
            }
        } else {
            content += "/>";
        }
        return content;
    }

}
