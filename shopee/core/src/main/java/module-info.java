module core {
    exports shopee.core;
    exports shopee.json;

    requires transitive com.fasterxml.jackson.databind; 
    requires com.fasterxml.jackson.core;

    opens shopee.core to com.fasterxml.jackson.databind;
}
