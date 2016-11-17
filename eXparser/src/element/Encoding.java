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

/**
 *
 * @author Claudis
 */
public class Encoding extends Node {
    
    private List<Attribute> attributes;

    /**
     *
     */
    public Encoding() {
        setName("xml");
    }

    /**
     *
     * @param content
     */
    public Encoding(String content) {
        setName("xml");
        setContent(content);
        attributes = new ArrayList<>();
        parse(content);
    }
    
    /**
     *
     * @param content
     */
    public void parse(String content) {
        char[] chars = content.toCharArray();
        for (int i = 0; i < chars.length; i++) {

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
    
    /**
     *
     * @return
     */
    @Override
    public String getContent() {
        String content = "";
        content += "<?" + getName();
        if (attributes != null && attributes.size() > 0) {
            for (int i = 0; i < attributes.size(); i++) {
                content += " " + attributes.get(i).getName() + "='" + attributes.get(i).getValue() + "'";
            }
        }
        content += " ?>\n";
        return content;
    }
    
    /**
     *
     * @return
     */
    public List<Attribute> getAttributes() {
        return attributes;
    }

    /**
     *
     * @param attributes
     */
    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }
    
}
