/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doc;

import node.Root;

/**
 *
 * @author Lucas
 */
public abstract class Document {
    
    private Root root;

    public Root getRoot() {
        return root;
    }

    public void setRoot(Root root) {
        this.root = root;
    }
    
    @Override
    public String toString() {
        return root.toString();
    }
}
