module com.seoproductions.templategenerationfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.seoproductions.templategenerationfx to javafx.fxml;
    exports com.seoproductions.templategenerationfx;
}