package salesmanagement.salesmanagement.Utils;

import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import static salesmanagement.salesmanagement.Utils.NotificationCode.NOT_AUTHORIZED;


public class NotificationSystem {
    private static String title;
    private static String content;


    public static void throwNotification(NotificationCode code, Stage stage) {
        switch (code) {
            case INVALID_INPUTS -> {
                title = "Error notification";
                content = "Please check all input fields again.";
            }
            case INVALID_LOGIN -> {
                title = "Login notification";
                content = "Invalid username or password!";
            }
            case INVALID_EMAIL -> {
                title = "Signup notification";
                content = "Invalid email!";
            }
            case NETWORK_ERROR -> {
                title = "Network notification";
                content = "Please check your network connection again!";
            }
            case INVALID_LOGIN_INFO -> {
                title = "Login notification";
                content = "Invalid username or email!";
            }
            case INVALID_SECURITY_CODE -> {
                title = "Login notification";
                content = "Invalid security code!";
            }
            case SUCCEED_VERIFY_MAIL -> {
                title = "Login notification";
                content = "Verify Mail Successfully!";
            }
            case SUCCEED_RESET_PASSWORD -> {
                title = "Login notification";
                content = "Reset Password Successfully!";
            }
            case SUCCEED_ADD_NEW_EMPLOYEE -> {
                title = "";
                content = "Added New Employee Successfully!";
            }
            case ERROR_EXPORTING -> {
                title = "";
                content = "Cannot export data!";
            }
            case SUCCEED_EXPORTING -> {
                title = "";
                content = "Export data successfully!";
            }
            case SUCCEED_ADD_CUSTOMER -> {
                title = "";
                content = "Added New Customer Successfully!";
            }
            case SUCCEED_CREATE_ORDER -> {
                title = "";
                content = "Order Created Successfully!";
            }
            case SUCCEED_EDIT_ORDER -> {
                title = "";
                content = "Order Saved Successfully!";
            }
            case SUCCEED_DELETE_ORDER -> {
                title = "";
                content = "Order Removed Successfully!";
            }
            case SUCCEED_CREATE_PRODUCT -> {
                title = "";
                content = "Product Added Successfully!";
            }
            case SUCCEED_EDIT_PRODUCT -> {
                title = "";
                content = "Product Saved Successfully!";
            }
            case SUCCEED_DELETE_PRODUCT -> {
                title = "";
                content = "Product Removed Successfully!";
            }
            case SUCCEED_DELETE_CUSTOMER -> {
                title = "";
                content = "Customer Removed Successfully!";
            }
            case SUCCEED_SAVE_INFO -> {
                title = "";
                content = "Saved Successfully!";
            }
            case NOT_AUTHORIZED -> {
                title = "Notification";
                content = "You do not have permission to perform this action!";
            }
        }
        Notifications notificationBuilder = Notifications.create()
                .title(title)
                .text(content)
                .owner(stage)
                .position(Pos.TOP_CENTER).hideAfter(Duration.seconds(2));
        notificationBuilder.show();
    }
}