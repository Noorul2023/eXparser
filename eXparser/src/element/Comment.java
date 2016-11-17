/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package element;

import node.Node;

/**
 *
 * @author Lucas
 */
public class Comment extends Node {

    private String value;

    public Comment() {
    }
    
    public Comment value(String value) {
        setValue(value);
        return this;
    }

    public Comment(String content) {
        setContent(content);
        parse(content);
    }

    public void parse(String content) {

        char[] chars = content.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            //get name
            if (chars[i] == '<') {
                String value = "";
                for (int h = i+3 + 1; h < chars.length; h++) {
                    if (chars[h] == '-' && chars[h+1] == '-' && chars[h+2] == '>') {
                        i = h;
                        setValue(value);
                        break;
                    } else {
                        value += chars[h];
                    }
                }
                break;
            }
        }
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getContent() {
        String content = "\n";
        content += getColumnSpaces() + "<!--" + getValue() + "-->";
        return content;
    }

}
