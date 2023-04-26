package salesmanagement.salesmanagement.ViewController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import salesmanagement.salesmanagement.SQLConnection;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class ViewController implements Initializable {
    protected SQLConnection sqlConnection;
    protected Stage stage;
    @FXML
    protected AnchorPane root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        root.setVisible(false);
    }

    public void setSqlConnection(SQLConnection sqlConnection) {
        this.sqlConnection = sqlConnection;
    }

    @FXML
    public void close() {
        root.setVisible(false);
    }

    public void show() {
        if (stage == null) stage = (Stage) root.getScene().getWindow();
        root.setVisible(true);
    }


}