/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import data.Attribute;
import doc.XML;
import element.Element;
import element.Tag;


/**
 *
 * @author Lucas
 */
public class Tester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        XML doc = (XML) Parser.xml(null);
        doc.getRoot().createEncoding();
//        doc.getRoot().getDoctype().setContent("<!DOCTYPE hibernate-configuration PUBLIC \"-//Hibernate/Hibernate Configuration DTD 3.0//EN\" \"http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd\">");
        
        Tag projectTag = new Tag("project", doc.getRoot());
        projectTag.add(new Attribute("xmlns", "http://www.netbeans.org/ns/project/1"));
        Tag type = new Tag("type", projectTag);
        type.add(new Element("org.netbeans.modules.web.project"));
        Tag configuration = new Tag("configuration", projectTag);
        Tag data = new Tag("data", configuration);
        data.add(new Attribute("xmlns", "http://www.netbeans.org/ns/web-project/3"));
        Tag projectName = new Tag("name", data);
        projectName.add(new Element("asaasassas"));
        
        Tag minimum_ant_version = new Tag("minimum-ant-version", data);
        minimum_ant_version.add(new Element("1.6.5"));
        
        Tag web_module_libraries = new Tag("web-module-libraries", data);
        
        Tag library = new Tag("library", web_module_libraries);
        library.add(new Attribute("dirs", "200"));
        Tag file = new Tag("file", library);
        file.add(new Element("${libs.jsf20.classpath}"));
        Tag path_in_war = new Tag("path-in-war", library);
        path_in_war.add(new Element("WEB-INF/lib"));
        
        library = new Tag("library", web_module_libraries);
        library.add(new Attribute("dirs", "200"));
        file = new Tag("file", library);
        file.add(new Element("${libs.hibernate4-support.classpath}"));
        path_in_war = new Tag("path-in-war", library);
        path_in_war.add(new Element("WEB-INF/lib"));
        
        library = new Tag("library", web_module_libraries);
        library.add(new Attribute("dirs", "200"));
        file = new Tag("file", library);
        file.add(new Element("${libs.jpa2-persistence.classpath}"));
        path_in_war = new Tag("path-in-war", library);
        path_in_war.add(new Element("WEB-INF/lib"));
        
        library = new Tag("library", web_module_libraries);
        library.add(new Attribute("dirs", "200"));
        file = new Tag("file", library);
        file.add(new Element("${file.reference.bootstrap.jar}"));
        path_in_war = new Tag("path-in-war", library);
        path_in_war.add(new Element("WEB-INF/lib"));
        
        library = new Tag("library", web_module_libraries);
        library.add(new Attribute("dirs", "200"));
        file = new Tag("file", library);
        file.add(new Element("${file.reference.commons-io-2.4.jar}"));
        path_in_war = new Tag("path-in-war", library);
        path_in_war.add(new Element("WEB-INF/lib"));
        
        library = new Tag("library", web_module_libraries);
        library.add(new Attribute("dirs", "200"));
        file = new Tag("file", library);
        file.add(new Element("${file.reference.derbyclient.jar}"));
        path_in_war = new Tag("path-in-war", library);
        path_in_war.add(new Element("WEB-INF/lib"));
        
        library = new Tag("library", web_module_libraries);
        library.add(new Attribute("dirs", "200"));
        file = new Tag("file", library);
        file.add(new Element("${file.reference.javaparser-1.0.8.jar}"));
        path_in_war = new Tag("path-in-war", library);
        path_in_war.add(new Element("WEB-INF/lib"));
        
        library = new Tag("library", web_module_libraries);
        library.add(new Attribute("dirs", "200"));
        file = new Tag("file", library);
        file.add(new Element("${file.reference.org.json-20120521.jar}"));
        path_in_war = new Tag("path-in-war", library);
        path_in_war.add(new Element("WEB-INF/lib"));
        
        library = new Tag("library", web_module_libraries);
        library.add(new Attribute("dirs", "200"));
        file = new Tag("file", library);
        file.add(new Element("${file.reference.primefaces-5.2.jar}"));
        path_in_war = new Tag("path-in-war", library);
        path_in_war.add(new Element("WEB-INF/lib"));
        
        library = new Tag("library", web_module_libraries);
        library.add(new Attribute("dirs", "200"));
        file = new Tag("file", library);
        file.add(new Element("${file.reference.BootsFaces-OSP-0.8.1-dist.jar}"));
        path_in_war = new Tag("path-in-war", library);
        path_in_war.add(new Element("WEB-INF/lib"));
        
        Tag web_module_additional_libraries = new Tag("web-module-additional-libraries", data);
        Tag source_roots = new Tag("source-roots", data);
        Tag root = new Tag("root", source_roots);
        root.add(new Attribute("id", "src.dir"));
        Tag test_roots = new Tag("test-roots", data);
        
//        FileManipulator htmlFile = new FileManipulator(new Path("E:\\TCC\\v19\\JWI\\web\\home.xhtml"));
//        String content = htmlFile.readFileAsString();
//
//        String text =   "<html>" + 
//                            "<header>" + 
//                            "</header>" +
//                            "<body>" +
//                            "<div>" +
//                                "<!-- comentary -->" +
//                                "<span>" +
//                                    "<a href=\"#\"> link </a> open" +
//                                "</span>" +
//                                "<div class=\"btn btn-default\">" +
//                                    "<i class=\"fa fa-user\"/>" +
//                                "</div>" +
//                            "</div>" +
//                            "</body>" +
//                        "</html>";
//        xhtml.XHTML document = xhtml.Parser.xhtml(content);
//        
//        document.getRoot().getHtml().add(new Attribute("xmlns:lucas", "http://sdsd"));
//        
//        document.toString());
        
//        
//        eXparser.XHTML doc = (eXparser.XHTML) Parser.xhtml(null);
//        doc.getRoot().createEncoding();
//        doc.getRoot().createDOCTYPE();
//        doc.getRoot().getDoctype().setContent("<!DOCTYPE hibernate-configuration PUBLIC \"-//Hibernate/Hibernate Configuration DTD 3.0//EN\" \"http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd\">");
//        doc.getRoot().add(new Tag("hibernate-configuration", null));
//        
//        Tag hibernateConfig = ((Tag) doc.getRoot().getChildrens().get(0));
//        Tag sessionFactory = new Tag("session-factory", null);
//        
//        sessionFactory.add(new Comment().value("config"));
//        
//        hibernateConfig.add(sessionFactory);
//        
//        Tag property = new Tag("property", null);
//        property.add(new Attribute("name", "hibernate.dialect"));
//        property.add(new Element("org.hibernate.dialect.DerbyDialect"));
//        sessionFactory.add(property);
//        
//        Tag property1 = new Tag("property", null);
//        property1.add(new Attribute("name", "hibernate.connection.driver_class"));
//        property1.add(new Element("org.apache.derby.jdbc.ClientDriver"));
//        sessionFactory.add(property1);
//        
//        sessionFactory.add(new Comment().value("conection to db"));
//        
//        Tag property2 = new Tag("property", null);
//        property2.add(new Attribute("name", "hibernate.connection.url"));
//        property2.add(new Element("jdbc:derby://localhost:1527/JWIDB"));
//        sessionFactory.add(property2);
//        
//        Tag property3 = new Tag("property", null);
//        property3.add(new Attribute("name", "hibernate.connection.username"));
//        property3.add(new Element("app"));
//        sessionFactory.add(property3);
//        
//        Tag property4 = new Tag("property", null);
//        property4.add(new Attribute("name", "hibernate.connection.password"));
//        property4.add(new Element("app"));
//        sessionFactory.add(property4);
//        
//        sessionFactory.add(new Comment().value("anothers"));
//        
//        Tag property5 = new Tag("property", null);
//        property5.add(new Attribute("name", "hibernate.hbm2ddl.auto"));
//        property5.add(new Element("update"));
//        sessionFactory.add(property5);
//        
//        Tag property6 = new Tag("property", null);
//        property6.add(new Attribute("name", "hibernate.current_session_context_class"));
//        property6.add(new Element("thread"));
//        sessionFactory.add(property6);
//        
//        sessionFactory.add(new Comment().value("entities"));
//        
//        "to String:\n" + doc.toString());     


//        XHTML doc = Jsoup.xhtml(content);
//        doc.outputSettings().escapeMode(Entities.EscapeMode.xhtml); //This will ensure the validity
//        doc.outputSettings().charset("UTF-8");
//        doc.outputSettings().escapeMode(EscapeMode.xhtml);
//        // Get back the string of the body.
//        String c = doc.toString();
//        c = c.replace("<head>", "");
//        c = c.replace("</head>", "");
//        c = c.replace("<body>", "");
//        c = c.replace("</body>", "");
//        c);
    }

}
