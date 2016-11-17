/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package node;

import element.Encoding;
import element.Doctype;
import element.Tag;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lucas
 */
public class Root extends Node {

    private List<Node> childrens;
    
    private Encoding encoding;
    
    private Doctype doctype;
    
    public void createEncoding() {
        encoding = new Encoding("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
    }
    
    public void createDOCTYPE() {
        doctype = new Doctype("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
    }
    
    public void add(Tag tag) {
        if (childrens == null) {
            childrens = new ArrayList<>();
        }
        childrens.add(tag);
    }

    public List<Node> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<Node> childrens) {
        this.childrens = childrens;
    }

    public Encoding getEncoding() {
        return encoding;
    }

    public void setEncoding(Encoding encoding) {
        this.encoding = encoding;
    }

    public Doctype getDoctype() {
        return doctype;
    }

    public void setDoctype(Doctype doctype) {
        this.doctype = doctype;
    }

    @Override
    public String toString() {
        return getContent();
    }
    
    @Override
    public String getContent() {
        String content = "";
        if(encoding!=null)
            content += encoding.getContent();
        if(doctype!=null)
            content += doctype.getContent();
        if(childrens != null && childrens.size() > 0) {
            for( int i = 0 ; i < childrens.size() ; i++ ) {
                content += childrens.get(i).getContent();
            }
        }
        return content;
    }
    
}
