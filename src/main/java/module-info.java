module ufpel.combat {
    requires javafx.controls;
    requires javafx.fxml;


    opens ufpel.combat to javafx.fxml;
    exports ufpel.combat;
}