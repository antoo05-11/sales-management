package salesmanagement.salesmanagement.ViewController;

import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import salesmanagement.salesmanagement.SalesComponent.SalesComponent;

public abstract class FilterView<T extends SalesComponent> extends ViewController {
    protected FilteredList<T> filteredList;

    @FXML
    protected abstract void applyFilter();

    public void setFilteredList(FilteredList<T> filteredList) {
        this.filteredList = filteredList;
    }

    public FilteredList<T> getFilteredList() {
        return filteredList;
    }
}
