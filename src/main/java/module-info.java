module com.labbooker.labbooker {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.labbooker.labbooker to javafx.fxml;
    exports com.labbooker.labbooker;
}