module core {
    exports core;
    exports core.Storage;

    requires transitive com.fasterxml.jackson.databind; 
    requires com.fasterxml.jackson.core;

    opens core to com.fasterxml.jackson.databind;
}
