module shopee.rest {

    requires com.fasterxml.jackson.databind;

    requires transitive core;

    requires spring.web;
    requires spring.boot;
    requires spring.core;
    requires spring.beans;
    requires spring.context;
    requires spring.boot.autoconfigure;
    requires java.net.http;

    opens shopee.rest to spring.web, spring.beans, spring.context, spring.core;

}