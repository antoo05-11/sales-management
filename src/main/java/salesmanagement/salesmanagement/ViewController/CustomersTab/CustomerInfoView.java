package salesmanagement.salesmanagement.ViewController.CustomersTab;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import salesmanagement.salesmanagement.SalesComponent.Action;
import salesmanagement.salesmanagement.SalesComponent.Customer;
import salesmanagement.salesmanagement.Utils.NotificationCode;
import salesmanagement.salesmanagement.Utils.NotificationSystem;
import salesmanagement.salesmanagement.ViewController.InfoView;

import java.sql.ResultSet;
import java.sql.SQLException;

import static salesmanagement.salesmanagement.SceneController.SceneController.runTask;

public class CustomerInfoView extends InfoView<Customer> implements CustomersTab {
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField contactTextField;
    @FXML
    private TextField customerNumberTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField SSNTextField;
    @FXML
    private ComboBox<String> rankComboBox;

    @Override
    public void show() {
        super.show();
        addButton.setVisible(true);
        customerNumberTextField.setEditable(false);
    }

    public void show(Customer customer) {
        super.show();
        super.selectedSalesComponent = customer;
        customerNumberTextField.setEditable(false);
        saveButton.setVisible(true);
        customerNumberTextField.setText(Integer.toString(customer.getCustomerNumber()));
        SSNTextField.setText(String.valueOf(customer.getSSN()));
        addressTextField.setText(customer.getAddress());
        contactTextField.setText(customer.getContact());
        rankComboBox.setValue(customer.getRank());
        nameTextField.setText(customer.getName());
    }

    @Override
    public void close() {
        super.close();
    }

    protected void resetData() {
        customerNumberTextField.setText("");
        SSNTextField.setText("");
        addressTextField.setText("");
        contactTextField.setText("");
        rankComboBox.setValue(null);
        nameTextField.setText("");
    }

    @FXML
    public void figureSave() {
        runTask(() -> {
                    String query = String.format("UPDATE customers SET customerName = '%s', customerSSN = '%s',  phone = '%s', addressLine = '%s' WHERE customerNumber = %d",
                            nameTextField.getText(), SSNTextField.getText(), contactTextField.getText(), addressTextField.getText(), selectedSalesComponent.getCustomerNumber());
                    sqlConnection.updateQuery(query, customerNumberTextField.getText(), Action.ComponentModified.CUSTOMER, Action.ActionCode.EDIT);
                }, () -> {
                    close();
                    parentController.show();
                    NotificationSystem.throwNotification(NotificationCode.SUCCEED_EDIT_CUSTOMER, stage);
                },
                parentController.getLoadingIndicator(), null);
    }

    @FXML
    public void add() {
        runTask(() -> {
                    close();
                    String query = String.format("insert into customers(customerName, phone, addressLine, customerSSN) values ('%s', '%s', '%s', '%s')",
                            nameTextField.getText(), contactTextField.getText(), addressTextField.getText(), SSNTextField.getText());

                    Action action;
                    try {
                        sqlConnection.updateQuery(query);
                        action = new Action(null,
                                Action.ComponentModified.CUSTOMER,
                                Action.ActionCode.CREATE_NEW,
                                Action.ResultCode.SUCCESSFUL);

                        query = "select customerNumber from customers where customerSSN = " + SSNTextField.getText();
                        ResultSet resultSet = sqlConnection.getDataQuery(query);
                        try {
                            while (resultSet.next()) {
                                customerNumberTextField.setText(String.valueOf(resultSet.getInt("customerNumber")));
                                action.setComponentModifiedID(customerNumberTextField.getText());
                            }
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    } catch (SQLException e) {
                        action = new Action( null,
                                Action.ComponentModified.CUSTOMER,
                                Action.ActionCode.CREATE_NEW,
                                Action.ResultCode.FAILED);
                    }
                    action.pushAction(sqlConnection);
                }, () -> {
                    parentController.show();
                    NotificationSystem.throwNotification(NotificationCode.SUCCEED_ADD_CUSTOMER, stage);
                },
                parentController.getLoadingIndicator(), null);
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
