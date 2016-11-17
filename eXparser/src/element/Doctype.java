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
public class Doctype extends Node {

    public Doctype() {
        setName("DOCTYPE");
    }
    
    public Doctype(String content) {
        setContent(content);
        setName("DOCTYPE");
    }
    

}
