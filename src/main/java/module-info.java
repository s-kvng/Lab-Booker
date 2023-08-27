module com.labbooker.labbooker {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.labbooker.labbooker to javafx.fxml;
    exports com.labbooker.labbooker;
    exports com.labbooker.labbooker.models;
    opens com.labbooker.labbooker.models to javafx.fxml;
    exports com.labbooker.labbooker.utils;
    opens com.labbooker.labbooker.utils to javafx.fxml;
}