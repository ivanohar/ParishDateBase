package com.ohar.pdb.controller;

import com.ohar.pdb.factory.AbstractServiceFactory;
import com.ohar.pdb.helper.StageHelper;
import com.ohar.pdb.model.Person;
import com.ohar.pdb.model.User;
import com.ohar.pdb.services.AuthorisationService;
import com.ohar.pdb.services.SearchPersonService;
import com.ohar.pdb.services.SearchUserService;
import com.ohar.pdb.services.impl.SearchUserServiceImpl;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FindWinController {
    private Long relationId;
    private WorkWinController controller;
    private SearchPersonService service = AbstractServiceFactory.getInstance().getSearchPersonService();
    private StageHelper helper = StageHelper.getInstance();
    private boolean choosePerson = false;

    @FXML
    private TableView<Person> personTable;

    @FXML
    private AnchorPane findPanel;

    @FXML
    private Button findButton;

    @FXML
    private TextField findField;

    @FXML
    private TableColumn<Person, String> idColumn;

    @FXML
    private TableColumn<Person, String> fioColumn;

    @FXML
    private TableColumn<Person, String> birthdayColumn;

    @FXML
    private TableColumn<Person, String> addressColumn;

    @FXML
    private TableColumn<Person, String> telephonColumn;

    @FXML
    private TableColumn<Person, String> mobilColumn;

    @FXML
    private Button createNewPersonButton;

    @FXML
    private Button cancelButton;

    @FXML
    void cancel(ActionEvent event) {
        helper.forwardMain(null);
    }

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId().toString()));
        fioColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFio()));
        birthdayColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBirthday().toString()));
        addressColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
        telephonColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelephon()));
        mobilColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMobile()));
    }

    @FXML
    void createNewPerson(ActionEvent event) {
        helper.forwardWorkWin(relationId);
    }

    @FXML
    void findPerson(ActionEvent event) {
        updatePersonTable();
    }

    @FXML
    void itemChooseClicked(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
            Long id = personTable.getSelectionModel().getSelectedItem().getId();
            if (choosePerson) {
                controller.returnChoosenId(id);
                ((Stage) personTable.getScene().getWindow()).close();
            } else {
                helper.forwardWorkWin(service.getPerson(id));
            }
        }
    }

    public void initializePerson(WorkWinController controller, Long personId) {
        this.controller = controller;
        this.relationId = personId;
        choosePerson = true;
        findField.setText(service.getPerson(personId).getAddress());
        updatePersonTable();
    }

    public TextField getFindField() {
        return findField;
    }

    private void updatePersonTable() {
        ObservableList<Person> personList = FXCollections.observableArrayList();
        personList.addAll(service.findPerson(findField.getText()));
        personTable.setItems(personList);
    }
}

