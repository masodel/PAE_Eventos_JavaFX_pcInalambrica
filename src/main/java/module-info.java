module ni.edu.uam.pae_eventos_javafx_pcinalambrica {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.pae_eventos_javafx_pcinalambrica to javafx.fxml;
    exports ni.edu.uam.pae_eventos_javafx_pcinalambrica;
}