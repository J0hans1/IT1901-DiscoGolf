module discogolf.restapi {
    requires spring.boot;
    requires spring.beans;
    requires spring.web;
    requires spring.context;
    requires spring.boot.autoconfigure;
    requires transitive discoGolf.core;

    exports discogolf.restapi;

    opens discogolf.restapi to spring.core;
}
