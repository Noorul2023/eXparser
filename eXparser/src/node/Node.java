/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package node;

/**
 *
 * @author Claudis
 */
public abstract class Node {

    private Node parent;

    private String name;

    private String content;
    
    public Node() {
    }

    public Node(String name) {
        this.name = name;
    }

    public Node(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        String c = "";
        c += content;
        return c;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return getContent();
    }

    public String getColumnSpaces() {
        if(parent != null && parent instanceof Root == false ) {
            return "    " + parent.getColumnSpaces();
        } else if(parent instanceof Root) {
            return "";
        } else {
            return "";
        }
    }
    
}
