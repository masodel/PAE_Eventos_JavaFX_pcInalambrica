module ni.edu.uam.pae_eventos_javafx_pcinalambrica {

    requires javafx.controls;
    requires javafx.fxml;

    exports ni.edu.uam.pae_eventos_javafx_pcinalambrica;
    exports ni.edu.uam.pae_eventos_javafx_pcinalambrica.controllers;
    exports ni.edu.uam.pae_eventos_javafx_pcinalambrica.modelos;

    opens ni.edu.uam.pae_eventos_javafx_pcinalambrica.controllers
            to javafx.fxml;

    opens ni.edu.uam.pae_eventos_javafx_pcinalambrica.modelos
            to javafx.base;
}