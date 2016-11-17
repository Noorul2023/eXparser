/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package element;

import node.Node;

/**
 *
 * @author Claudis
 */
public class Element extends Node {

    public Element() {
    }

    public Element(String text) {
        setContent(text);
    }

    @Override
    public String getColumnSpaces() {
        return "";
    }

}
