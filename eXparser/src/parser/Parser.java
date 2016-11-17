/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import node.Root;
import node.Node;
import doc.XML;
import doc.XHTML;
import element.Comment;
import element.Encoding;
import element.CDATA;
import element.Doctype;
import element.Tag;
import element.Element;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lucas
 */
public class Parser {

    public static XHTML xhtml(String content) {
        XHTML doc = new XHTML();
        if(content != null) {
            List<String> fragments = transformInFragmentsForXHTML(content);
            Root root = new Root();
            doc.setRoot(root);
            fragments = encoding(fragments, root);
            fragments = doctype(fragments, root);
            root.setParent(null);
            root.setName("Root");
            root.setContent("root");
            List<Element> elements = transformFragmentsInElements(fragments);
            root.setChildrens(new ArrayList<Node>());
            parseNodesForXHTML(root, elements, 0);
        } else {
            Root root = new Root();
            doc.setRoot(root);
            root.setName("Root");
        }
        return doc;
    }
    
    public static XML xml(String content) {
        XML doc = new XML();
        if(content != null) {
            List<String> fragments = transformInFragmentsForXML(content);
            Root root = new Root();
            doc.setRoot(root);
            fragments = encoding(fragments, root);
            fragments = doctype(fragments, root);
            root.setParent(null);
            root.setName("Root");
            root.setContent("root");
            List<Element> elements = transformFragmentsInElements(fragments);
            root.setChildrens(new ArrayList<Node>());
            parseNodesForXML(root, elements, 0);
        } else {
            Root root = new Root();
            doc.setRoot(root);
            root.setName("Root");
        }
        return doc;
    }

    public static void printChilds(Node element) {
        if (element instanceof Root) {
            for (Node child : ((Root) element).getChildrens()) {
                if (child instanceof Tag) {
                    if (((Tag) child).getChildrens() != null) {
                        printChilds(child);
                    }
                }
            }
        }
    }

    public static List<String> transformInFragmentsForXHTML(String content) {

        List<String> fragments = new ArrayList<>();

        String fragment = content.replace("<", "\n<");
        fragment = fragment.replace("/>", "/>\n");
        fragment = fragment.replace(">", ">\n");

        String[] fragment_array = fragment.split("\n");
        
        for (int i = 0; i < fragment_array.length; i++) {
            if (fragment_array[i].contains("<") && !fragment_array[i].contains("<!--")) {
                String text = "";
                for (int h = i; h < fragment_array.length; h++) {
                    text += fragment_array[h];
                    if (fragment_array[h].contains(">")) {
                        fragments.add(text);
                        i = h;
                        break;
                    }
                }
            } else if (fragment_array[i].contains("<!--")) {
                String text = "";
                for (int h = i; h < fragment_array.length; h++) {
                    text += fragment_array[h];
                    if (fragment_array[h].contains("-->")) {
                        fragments.add(text);
                        i = h;
                        break;
                    }
                }
            } else if (!fragment_array[i].isEmpty()) {
                fragments.add(fragment_array[i]);
            }
        }

        return fragments;

    }
    
    public static List<String> transformInFragmentsForXML(String content) {

        List<String> fragments = new ArrayList<>();

        String fragment = content.replace("<", "\n<");
        fragment = fragment.replace("/>", "/>\n");
        fragment = fragment.replace(">", ">\n");

        String[] fragment_array = fragment.split("\n");
        
        for (int i = 0; i < fragment_array.length; i++) {
            if (fragment_array[i].contains("<") && !fragment_array[i].contains("<!--") && !fragment_array[i].contains("<![CDATA[")) {
                String text = "";
                for (int h = i; h < fragment_array.length; h++) {
                    text += fragment_array[h];
                    if (fragment_array[h].contains(">")) {
                        fragments.add(text);
                        i = h;
                        break;
                    }
                }
            } else if (fragment_array[i].contains("<!--")) {
                String text = "";
                for (int h = i; h < fragment_array.length; h++) {
                    text += fragment_array[h];
                    if (fragment_array[h].contains("-->")) {
                        fragments.add(text);
                        i = h;
                        break;
                    }
                }
            } else if(fragment_array[i].contains("<![CDATA[")) {
                String text = "";
                for (int h = i; h < fragment_array.length; h++) {
                    text += fragment_array[h];
                    if (fragment_array[h].contains("]>")) {
                        fragments.add(text);
                        i = h;
                        break;
                    }
                }
            }else if (!fragment_array[i].isEmpty()) {
                fragments.add(fragment_array[i]);
            }
        }

        return fragments;

    }

    public static List<Element> transformFragmentsInElements(List<String> fragments) {

        List<Element> elements = new ArrayList<>();

        for (int i = 0; i < fragments.size(); i++) {
            Element element = new Element();
            element.setContent(fragments.get(i));
            elements.add(element);
        }
        return elements;

    }

    public static int parseNodesForXHTML(Node parent, List<Element> elements, int pos) {
        for (int i = pos; i <= elements.size(); i++) {
            if (i != elements.size() && elements.get(i).getContent().contains("<") && !elements.get(i).getContent().contains("</") && elements.get(i).getContent().contains(">") && !elements.get(i).getContent().contains("/>") && !elements.get(i).getContent().contains("<!--")) {
                Tag tag = new Tag(null, elements.get(i).getContent(), parent);
//                tag.setParent(parent);
                tag.setChildrens(new ArrayList<Node>());
                if(parent instanceof Root) {
                    ((Root) parent).getChildrens().add(tag);
                } else {
                    ((Tag) parent).getChildrens().add(tag);
                }
//                elements.get(i).setChildrens(new ArrayList<>());
//                parent.getChildrens().add(elements.get(i));
                i = parseNodesForXHTML(tag, elements, i + 1);
            } else if (i != elements.size() && elements.get(i).getContent().contains("</")) {
                return i;
            } else if (i != elements.size() && elements.get(i).getContent().contains("/>")) {
                Tag tag = new Tag(null, elements.get(i).getContent(), parent);
//                tag.setParent(parent);
                if(parent instanceof Root) {
                    ((Root) parent).getChildrens().add(tag);
                } else {
                    ((Tag) parent).getChildrens().add(tag);
                }
//                parent.getChildrens().add(elements.get(i));
            } else if (i != elements.size() && elements.get(i).getContent().contains("<!--")) {
                Comment comment = new Comment(elements.get(i).getContent());
                comment.setParent(parent);
                if(parent instanceof Root) {
                    ((Root) parent).getChildrens().add(comment);
                } else {
                    ((Tag) parent).getChildrens().add(comment);
                }
            } else if (i != elements.size()) {
                elements.get(i).setParent(parent);
                if(parent instanceof Root) {
                    ((Root) parent).getChildrens().add(elements.get(i));
                } else {
                    ((Tag) parent).getChildrens().add(elements.get(i));
                }
            }
        }
        return 0;
    }
    
    public static int parseNodesForXML(Node parent, List<Element> elements, int pos) {
        for (int i = pos; i <= elements.size(); i++) {
            if (i != elements.size() && elements.get(i).getContent().contains("<") && !elements.get(i).getContent().contains("</") && elements.get(i).getContent().contains(">") && !elements.get(i).getContent().contains("/>") && !elements.get(i).getContent().contains("<!--") && !elements.get(i).getContent().contains("<[CDATA[")) {
                Tag tag = new Tag(null, elements.get(i).getContent(), parent);
//                tag.setParent(parent);
                tag.setChildrens(new ArrayList<Node>());
                if(parent instanceof Root) {
                    ((Root) parent).getChildrens().add(tag);
                } else {
                    ((Tag) parent).getChildrens().add(tag);
                }
//                elements.get(i).setChildrens(new ArrayList<>());
//                parent.getChildrens().add(elements.get(i));
                i = parseNodesForXHTML(tag, elements, i + 1);
            } else if (i != elements.size() && elements.get(i).getContent().contains("</")) {
                return i;
            } else if (i != elements.size() && elements.get(i).getContent().contains("/>")) {
                Tag tag = new Tag(null, elements.get(i).getContent(), parent);
//                tag.setParent(parent);
                if(parent instanceof Root) {
                    ((Root) parent).getChildrens().add(tag);
                } else {
                    ((Tag) parent).getChildrens().add(tag);
                }
//                parent.getChildrens().add(elements.get(i));
            } else if (i != elements.size() && elements.get(i).getContent().contains("<!--") && !elements.get(i).getContent().contains("<![CDATA[")) {
                Comment comment = new Comment(elements.get(i).getContent());
                comment.setParent(parent);
                if(parent instanceof Root) {
                    ((Root) parent).getChildrens().add(comment);
                } else {
                    ((Tag) parent).getChildrens().add(comment);
                }
            }  else if (i != elements.size() && elements.get(i).getContent().contains("<![CDATA[")) {
                CDATA cdata = new CDATA(elements.get(i).getContent());
                cdata.setParent(parent);
                if(parent instanceof Root) {
                    ((Root) parent).getChildrens().add(cdata);
                } else {
                    ((Tag) parent).getChildrens().add(cdata);
                }
            } else if (i != elements.size()) {
                elements.get(i).setParent(parent);
                if(parent instanceof Root) {
                    ((Root) parent).getChildrens().add(elements.get(i));
                } else {
                    ((Tag) parent).getChildrens().add(elements.get(i));
                }
            }
        }
        return 0;
    }

    public static List<String> encoding(List<String> content, Root root) {
        Encoding encoding = null;
        String encoding_content = "";
        List<String> contents = new ArrayList<>();

        int i = 0;
        for (String s : content) {
            if (s.contains("<?xml")) {
                encoding_content = s;
            } else if (s == null) {

            } else if (s.isEmpty()) {

            } else if (s.equals("")) {

            } else {
                contents.add(s);
            }
            i++;
        }
        if(!encoding_content.isEmpty())
            encoding = new Encoding(encoding_content);
        root.setEncoding(encoding);
        return contents;
    }

    public static List<String> doctype(List<String> content, Root root) {
        Doctype doctype = null;
        String doctype_content = "";
        List<String> contents = new ArrayList<>();

        int i = 0;
        for (String s : content) {
            if (s != null && s.contains("<!DOCTYPE")) {
               doctype_content += s;
            } else if (s == null) {

            } else if (s.isEmpty()) {

            } else if (s.equals("")) {

            } else {
                contents.add(s);
            }
            i++;
        }
        doctype = new Doctype(doctype_content);
        root.setDoctype(doctype);
        return contents;
    }

}


