module com.zack.tpgestionconsultantion {
    requires javafx.fxml;
    requires java.sql;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.controls;

    opens com.zack.tpgestionconsultantion.controllers to javafx.fxml;
    opens com.zack.tpgestionconsultantion.entities to javafx.base;
    exports com.zack.tpgestionconsultantion;

}