package salesmanagement.salesmanagement.ViewController.ProductLinesTab;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import salesmanagement.salesmanagement.SalesComponent.Action;
import salesmanagement.salesmanagement.SalesComponent.ProductLine;
import salesmanagement.salesmanagement.Utils.NotificationCode;
import salesmanagement.salesmanagement.Utils.NotificationSystem;
import salesmanagement.salesmanagement.ViewController.InfoView;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static salesmanagement.salesmanagement.SceneController.SceneController.runTask;

public class ProductLineInfoView extends InfoView<ProductLine> implements ProductLinesTab {
    @FXML
    private Label boxLabel;
    @FXML
    private TextField descriptionTextField;
    @FXML
    private TextField mainProductVendorTextField;
    @FXML
    private TextField productLineTextField;
    @FXML
    private TextField quantityInStockTextField;
    @FXML
    private TextField totalRevenueTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
    }

    @Override
    protected void figureSave() {
        runTask(() -> {
            close();
            String query = String.format("update productlines set textDescription = '%s' where productLine = '%s'", descriptionTextField.getText(), productLineTextField.getText());
            sqlConnection.updateQuery(query, productLineTextField.getText(),
                    Action.ComponentModified.PRODUCTLINES, Action.ActionCode.EDIT);
        }, () -> {
            parentController.show();
            NotificationSystem.throwNotification(NotificationCode.SUCCEED_SAVE_INFO, stage);
        }, parentController.getLoadingIndicator(), null);
    }

    @Override
    protected void add() {
        runTask(() -> {
            close();
            String query = String.format("insert into productlines (productLine, textDescription) VALUES ('%s', '%s');", descriptionTextField.getText(), productLineTextField.getText());
            Action action;
            try {
                sqlConnection.updateQuery(query);
                action = new Action(productLineTextField.getText(),
                        Action.ComponentModified.PRODUCTLINES,
                        Action.ActionCode.CREATE_NEW,
                        Action.ResultCode.SUCCESSFUL);


            } catch (SQLException e) {
                action = new Action(null,
                        Action.ComponentModified.PRODUCTLINES,
                        Action.ActionCode.CREATE_NEW,
                        Action.ResultCode.FAILED);
            }
            action.pushAction(sqlConnection);
        }, () -> {
            parentController.show();
            NotificationSystem.throwNotification(NotificationCode.SUCCEED_SAVE_INFO, stage);
        }, parentController.getLoadingIndicator(), null);
    }

    public void show(ProductLine productLine) {
        super.show();
        boxLabel.setText("Product Line Information");
        productLineTextField.setEditable(false);
        saveButton.setVisible(true);
        quantityInStockTextField.setText(String.valueOf(productLine.getNumberOfProducts()));
        productLineTextField.setText(productLine.getProductLine());
        mainProductVendorTextField.setText(productLine.getMainProductVendor());
        totalRevenueTextField.setText(String.valueOf(productLine.getTotalRevenue()));
        descriptionTextField.setText(productLine.getDescription());
    }

    @Override
    public void show() {
        super.show();

        boxLabel.setText("Add New Product Line");
        productLineTextField.setEditable(true);
        addButton.setVisible(true);
    }

    @Override
    protected void resetData() {
        super.resetData();
        totalRevenueTextField.setText("");
        productLineTextField.setText("");
        descriptionTextField.setText("");
        mainProductVendorTextField.setText("");
        quantityInStockTextField.setText("");
    }

    @Override
    public void addRegexChecker() {

    }

    @Override
    public boolean validInput() {
        return true;
    }

    @Override
    public void removeInvalidAlert() {

    }
}
