package com.ohar.pdb.controller;

import com.ohar.pdb.factory.AbstractServiceFactory;
import com.ohar.pdb.helper.StageHelper;
import com.ohar.pdb.model.Person;
import com.ohar.pdb.model.enums.*;
import com.ohar.pdb.services.SearchPersonService;
import com.ohar.pdb.services.ValidationService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;

public class WorkWinController {

    private SearchPersonService service = AbstractServiceFactory.getInstance().getSearchPersonService();
    private ValidationService validationService = AbstractServiceFactory.getInstance().getValidationService();
    private StageHelper helper = StageHelper.getInstance();
    private RelationPerson relationPerson;
    private WorkWinController parentController;
    private List<String> childrenList = new ArrayList<>();
    private List<String> otherMemberList = new ArrayList<>();
    private List<String> visitYearsList = new ArrayList<>();

    @FXML
    private AnchorPane workWinToPrint;

    @FXML
    private Text familyMemberText;

    @FXML
    private Text wifeText;

    @FXML
    private Text religionLifeText;

    @FXML
    private Text ersonalDateText;

    @FXML
    private Text commentsText;

    @FXML
    private Text addresText;

    @FXML
    private Text childrenText;

    @FXML
    private Text otherMemberText;

    @FXML
    private Text lastVisitYearTex;

    @FXML
    private TextField idField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField cityField;

    @FXML
    private TextField streetField;

    @FXML
    private TextField numberHouseField;

    @FXML
    private TextField educationField;

    @FXML
    private TextField professionField;

    @FXML
    private TextField fatherNameField;

    @FXML
    private TextField partnerField;

    @FXML
    private TextField idChild1Field;

    @FXML
    private TextField children1Field;

    @FXML
    private TextField numberFlatField;

    @FXML
    private TextField otherMember1Field;

    @FXML
    private TextField telephonField;

    @FXML
    private TextField mobileField;

    @FXML
    private TextField idPartnerField;

    @FXML
    private TextField idOther1Field;

    @FXML
    private TextArea commentsField;

    @FXML
    private TextField lastVisitYear;

    @FXML
    private DatePicker birthdayDate;

    @FXML
    private ComboBox<String> religionEducationField;

    @FXML
    private ComboBox<String> familyStatusField;

    @FXML
    private ComboBox<String> udzelImshyField;

    @FXML
    private ComboBox<String> spovedzField;

    @FXML
    private ComboBox<String> genderField;

    @FXML
    private DatePicker hrostDate;

    @FXML
    private DatePicker kamuniyaDate;

    @FXML
    private DatePicker kanfirmazyiaDate;

    @FXML
    private DatePicker suzhenstvaDate;

    @FXML
    private CheckBox isHrost;

    @FXML
    private CheckBox isKamuniya;

    @FXML
    private CheckBox isKanfirmazyia;

    @FXML
    private CheckBox isSuzhenstva;

    @FXML
    private Button deleteButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button other1RemoveButton;

    @FXML
    private Button partnerPlusButton;

    @FXML
    private Button partnerRemoveButton;

    @FXML
    private Button child1RemoveButton;

    @FXML
    private Button child1PlusButton;

    @FXML
    private Button otherMember1PlusButton;

    @FXML
    private TableView<Person> childrenTable;

    @FXML
    private TableColumn<Person, String> idColumn;

    @FXML
    private TableColumn<Person, String> fioColumn;

    @FXML
    private TableColumn<Person, String> birthdayColumn;

    @FXML
    private TableColumn<Person, Person> deleteButtonColumn;

    @FXML
    private TableView<Person> otherTable;

    @FXML
    private TableColumn<Person, String> idOtherColumn;

    @FXML
    private TableColumn<Person, String> fioOtherColumn;

    @FXML
    private TableColumn<Person, String> birthdayOtherColumn;

    @FXML
    private TableColumn<Person, Person> deleteOtherButtonColumn;

    @FXML
    private ListView<String> visitYears;

    @FXML
    void isHrostAction(ActionEvent event) {
        hrostDate.setDisable(!isHrost.isSelected());
    }

    @FXML
    void isKamuniyaAction(ActionEvent event) {
        kamuniyaDate.setDisable(!isKamuniya.isSelected());
    }

    @FXML
    void isKanfirmazyiaAction(ActionEvent event) {
        kanfirmazyiaDate.setDisable(!isKanfirmazyia.isSelected());
    }

    @FXML
    void isSuzhenstvaAction(ActionEvent event) {
        suzhenstvaDate.setDisable(!isSuzhenstva.isSelected());
    }


    private void initRelationPerson(RelationPerson relation) {
        relationPerson = relation;
        Long personId = idField.getText().isEmpty() ? null : Long.parseLong(idField.getText());
        helper.forwardSearch(this, personId);
    }

    @FXML
    void addPartner(ActionEvent event) {
        initRelationPerson(RelationPerson.PARTNER);
    }

    @FXML
    void addChild1(ActionEvent event) {
        initRelationPerson(RelationPerson.CHILD);
    }

    @FXML
    void addOther1(ActionEvent event) {
        initRelationPerson(RelationPerson.OTHER);
    }

    @FXML
    void removePartner(ActionEvent event) {
        idPartnerField.clear();
        partnerField.clear();
    }

    @FXML
    void removeChild1(ActionEvent event) {
        idChild1Field.clear();
        otherMember1Field.clear();
    }

    @FXML
    void removeOther1(ActionEvent event) {
        idOther1Field.clear();
        otherMember1Field.clear();
    }

    @FXML
    void changeGender(ActionEvent event) {
        if (Gender.MALE.getText().equals(genderField.getValue())) {
            wifeText.setText("Жонка:");
            partnerField.setPromptText("Жонка");
        } else {
            wifeText.setText("Муж:");
            partnerField.setPromptText("Муж");
        }
    }

    @FXML
    void cancel(ActionEvent event) {
        helper.forwardSearch();
    }

    @FXML
    void delete(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("ParishDateBase");
        alert.setContentText("Жадаеце выдаліць?");
        ButtonType okButton = new ButtonType("Так", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("Не", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(okButton, noButton);
        alert.showAndWait().ifPresent(type -> {
            if (type == okButton) {
                service.deletePerson(Long.parseLong(idField.getText()));
                helper.forwardSearch();
            }
        });
    }

    @FXML
    public void initialize() {
        deleteButton.setVisible(false);
        genderField.getItems().addAll(Gender.MALE.getText(), Gender.FEMALE.getText());
        udzelImshyField.getItems().addAll(UdzelImshy.OFTEN.getText(), UdzelImshy.RARELY.getText(), UdzelImshy.NEVER.getText());
        spovedzField.getItems().addAll(Spovedz.OFTEN.getText(), Spovedz.RARELY.getText(), Spovedz.NEVER.getText());
        familyStatusField.getItems().addAll(FamilyStatus.MARR.getText(), FamilyStatus.NOTMARR.getText(), FamilyStatus.DIVORCED.getText(), FamilyStatus.SEPARATED.getText(), FamilyStatus.UNREGISTERED.getText(), FamilyStatus.WIDOWER.getText());
        religionEducationField.getItems().addAll(ReligionEducation.YES.getText(), ReligionEducation.RERELY.getText(), ReligionEducation.NO.getText());

        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId().toString()));
        fioColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFio()));
        birthdayColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBirthday().toString()));

        initializeDeleteButtonTable(childrenTable, deleteButtonColumn, childrenList);
        idOtherColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId().toString()));
        fioOtherColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFio()));
        birthdayOtherColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBirthday().toString()));
        initializeDeleteButtonTable(otherTable, deleteOtherButtonColumn, otherMemberList);

        hrostDate.setDisable(true);
        kamuniyaDate.setDisable(true);
        kanfirmazyiaDate.setDisable(true);
        suzhenstvaDate.setDisable(true);

    }

    private void initializeDeleteButtonTable(TableView<Person> table, TableColumn<Person, Person> column, List<String> list) {
        column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Person, Person>, ObservableValue<Person>>() {
            @Override
            public ObservableValue<Person> call(TableColumn.CellDataFeatures<Person, Person> features) {
                return new ReadOnlyObjectWrapper<>(features.getValue());
            }
        });
        column.setCellFactory(param -> new TableCell<Person, Person>() {
            private final Button deleteButtonInTable = new Button("-");

            @Override
            protected void updateItem(Person person, boolean empty) {
                super.updateItem(person, empty);
                if (person == null) {
                    setGraphic(null);
                    return;
                }
                deleteButtonInTable.setOnAction(event -> {
                    Person personToDelete = getTableView().getItems().get(getIndex());
                    list.remove(personToDelete.getId().toString());
                    updateTable(table, list);
                });
                setGraphic(deleteButtonInTable);
            }
        });
    }

    @FXML
    void save(ActionEvent event) {
        Person person = new Person();
        person.setId(idField.getText().isEmpty() ? null : Long.parseLong(idField.getText()));
        person.setLastName(checkValue(lastNameField.getText()));
        person.setFirstName(checkValue(firstNameField.getText()));
        person.setFatherName(checkValue(fatherNameField.getText()));
        person.setBirthday(birthdayDate.getValue());
        person.setGender(Gender.getGenderByText(genderField.getValue()));
        person.setEducation(checkValue(educationField.getText()));
        person.setProfession(checkValue(professionField.getText()));
        person.setCity(checkValue(cityField.getText()));
        person.setStreet(checkValue(streetField.getText()));
        person.setNumberHouse(checkValue(numberHouseField.getText()));
        person.setNumberFlat(checkValue(numberFlatField.getText()));
        person.setTelephon(checkValue(telephonField.getText()));
        person.setMobile(checkValue(mobileField.getText()));
        person.setHrost(isHrost.isSelected());
        if (person.getHrost()) {
            person.setHrostDate(hrostDate.getValue());
        }
        person.setKamuniya(isKamuniya.isSelected());
        if (person.getKamuniya()) {
            person.setKamuniyaDate(kamuniyaDate.getValue());
        }
        person.setKanfirmazyia(isKanfirmazyia.isSelected());
        if (person.getKanfirmazyia()) {
            person.setKanfirmazyiaDate(kanfirmazyiaDate.getValue());
        }
        person.setSuzhenstva(isSuzhenstva.isSelected());
        if (person.getSuzhenstva()) {
            person.setSuzhenstvaDate(suzhenstvaDate.getValue());
        }
        person.setReligionEducation(ReligionEducation.getReligionEducationByText(religionEducationField.getValue()));
        person.setUdzelImshy(UdzelImshy.getUdzelImshyByText(udzelImshyField.getValue()));
        person.setSpovedz(Spovedz.getSpovedzByText(spovedzField.getValue()));
        person.setComments(commentsField.getText());
        person.setPartner(idPartnerField.getText().isEmpty() ? null : service.getPerson(Long.parseLong(idPartnerField.getText())));
        person.setFamilyStatus(FamilyStatus.getFamilyStatusByText(familyStatusField.getValue()));
        person.setChildren(childrenList.size() > 0 ? childrenList : null);
        person.setOtherMember(otherMemberList.size() > 0 ? otherMemberList : null);
        String lastVisitYear = this.lastVisitYear.getText();
        if (lastVisitYear != null && !lastVisitYear.isEmpty()) {
            visitYearsList.add(lastVisitYear);
            person.setVisitYears(visitYearsList);
        }
        if (validatePerson(person)) {
            if (person.getId() == null || "".equals(person.getId())) {
                person = service.createPerson(person);
            } else {
                person = service.updatePerson(person);
            }
            if (parentController != null) {
                parentController.returnChoosenId(person.getId());
                ((Stage) idField.getScene().getWindow()).close();
            } else {
                helper.forwardSearch();
            }
        }
    }
    private boolean validatePerson(Person person) {
        List<String> errors = validationService.validatePerson(person);
        if (errors.size() > 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Валідацыя асобы");
            alert.setHeaderText(null);
            alert.setContentText(errors.toString());
            alert.showAndWait();
            return false;
        }
        return true;
    }
    private String checkValue(String value) {
        return (value == null || value.isEmpty()) ? null : value;
    }

    public void initializePerson(Person person) {
        deleteButton.setVisible(true);
        idField.setText(person.getId().toString());
        lastNameField.setText(person.getLastName());
        firstNameField.setText(person.getFirstName());
        fatherNameField.setText(person.getFatherName());
        birthdayDate.setValue(person.getBirthday());
        genderField.setValue(person.getGender() != null ? person.getGender().getText() : "");
        educationField.setText(person.getEducation() != null ? person.getEducation() : "");
        professionField.setText(person.getProfession());
        addressToTable(person);
        mobileField.setText(person.getMobile());
        isHrost.setSelected(person.getHrost());
        hrostDate.setDisable(!isHrost.isSelected());
        hrostDate.setValue(person.getHrostDate());
        isKamuniya.setSelected(person.getKamuniya());
        kamuniyaDate.setDisable(!isKamuniya.isSelected());
        kamuniyaDate.setValue(person.getKamuniyaDate());
        isKanfirmazyia.setSelected(person.getKanfirmazyia());
        kanfirmazyiaDate.setDisable(!isKanfirmazyia.isSelected());
        kanfirmazyiaDate.setValue(person.getKanfirmazyiaDate());
        isSuzhenstva.setSelected(person.getSuzhenstva());
        suzhenstvaDate.setDisable(!isSuzhenstva.isSelected());
        suzhenstvaDate.setValue(person.getSuzhenstvaDate());
        religionEducationField.setValue(person.getReligionEducation() != null ? person.getReligionEducation().getText() : "");
        udzelImshyField.setValue(person.getUdzelImshy() != null ? person.getUdzelImshy().getText() : "");
        spovedzField.setValue(person.getSpovedz() != null ? person.getSpovedz().getText() : "");
        if (person.getPartner() != null) {
            initializePartner(person.getPartner().getId());
        }
        familyStatusField.setValue(person.getFamilyStatus() != null ? person.getFamilyStatus().getText() : "");
        childrenList = person.getChildren();
        updateTable(childrenTable, childrenList);
        otherMemberList = person.getOtherMember();
        updateTable(otherTable, otherMemberList);
        visitYearsList = person.getVisitYears();
        visitYears.setItems(FXCollections.observableArrayList(visitYearsList));
        lastVisitYear.setText(person.getLastVisitYear());
        commentsField.setText(person.getComments());

        initializeDeleteButtonTable(childrenTable, deleteButtonColumn, childrenList);
        initializeDeleteButtonTable(otherTable, deleteOtherButtonColumn, otherMemberList);
    }

    private void addressToTable(Person person) {
        cityField.setText(person.getCity());
        streetField.setText(person.getStreet());
        numberHouseField.setText(person.getNumberHouse());
        numberFlatField.setText(person.getNumberFlat());
        telephonField.setText(person.getTelephon());
    }

    public void returnChoosenId(Long id) {
        switch (relationPerson) {
            case PARTNER:
                initializePartner(id);
                break;
            case CHILD:
                initializeChildren(id);
                break;
            case OTHER:
                initializeOther(id);
                break;
        }
    }

    private void fillRelationPerson(Long id, TextField idField, TextField inputField) {
        if (id != null) {
            idField.setText(id.toString());
            inputField.setText(service.getPerson(id).getFio());
        }
    }

    private void initializePartner(Long id) {
        fillRelationPerson(id, idPartnerField, partnerField);
    }

    private void initializeChildren(Long id) {
        childrenList.add(id.toString());

        ObservableList<Person> list = FXCollections.observableArrayList();
        list.addAll(service.getPersonsByIds(childrenList));
        childrenTable.setItems(list);
    }

    private void initializeOther(Long id) {
        otherMemberList.add(id.toString());
        ObservableList<Person> list = FXCollections.observableArrayList();
        list.addAll(service.getPersonsByIds(otherMemberList));
        otherTable.setItems(list);
    }

    public void initializeController(WorkWinController controller, Long relationId) {
        this.parentController = controller;
        Person person = service.getPerson(relationId);
        addressToTable(person);
    }

    public TextField getIdField() {
        return idField;
    }

    private void updateTable(TableView<Person> table, List<String> list) {
        ObservableList<Person> models = FXCollections.observableArrayList();
        if (list.size() > 0) {
            models.addAll(service.getPersonsByIds(list));
        }
        table.setItems(models);
    }
    public void print (final Node node) {
        Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout = printer.createPageLayout(Paper.A5, PageOrientation.PORTRAIT, Printer.MarginType.HARDWARE_MINIMUM);
        final double scaleX = pageLayout.getPrintableWidth() / node.getBoundsInParent().getWidth();
        final double scaleY = pageLayout.getPrintableHeight() / node.getBoundsInParent().getHeight();
        node.getTransforms().add(new Scale(scaleX, scaleY));

        PrinterJob job =PrinterJob.createPrinterJob();
        if (job != null ){

            boolean success = job.printPage(node);
            System.out.println("printed");
            if (success){
                System.out.println(success);
                job.endJob();
            }
        }

    }
}
