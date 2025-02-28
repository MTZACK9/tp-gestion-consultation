module com.zack.tpgestionconsultantion {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.zack.tpgestionconsultantion.controllers to javafx.fxml;
    opens com.zack.tpgestionconsultantion.entities to javafx.base;
    exports com.zack.tpgestionconsultantion;

}