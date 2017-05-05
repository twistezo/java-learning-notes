package com.twistezo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;


/**
 * @author twistezo (13.03.2017)
 */
public class SceneController {
    private final String NOTE_FILE_PATH = "note.txt";
    private int currentPosition;
    private ArrayList<String> noteList;
    private Random random;
    private Scanner scanner;
    @FXML private TextArea textArea;
    @FXML private AnchorPane anchorPane;

    @FXML
    private void initialize() {
        noteList = new ArrayList<>();
        random = new Random();
        scanner = null;
        generateNoteTextFromFile();
        switchNoteColor();
        showNoteInTextArea(random.nextInt(noteList.size()));
    }

    private void switchNoteColor() {
        anchorPane.getStyleClass().clear();
        int x = random.nextInt(2);
        switch(x) {
            case 0:
                /* blue */
                anchorPane.getStyleClass().add("blue");
                break;
            case 1:
                /* green */
                anchorPane.getStyleClass().add("green");
                break;
        }
    }

    @FXML
    private void handlePrevButtonOnAction(ActionEvent actionEvent) {

        if(currentPosition > 0) {
            currentPosition = getCurrentPosition()-1;
            showNoteInTextArea(currentPosition);
            switchNoteColor();
            setCurrentPosition(currentPosition);
        }
    }

    @FXML
    private void handleRandButtonOnAction(ActionEvent actionEvent) {
        int x = random.nextInt(2);
        currentPosition = random.nextInt(noteList.size());
        showNoteInTextArea(currentPosition);
        switchNoteColor();
        setCurrentPosition(currentPosition);
    }

    @FXML
    private void handleNextButtonOnAction(ActionEvent actionEvent) {

        if(currentPosition < noteList.size()-1) {
            currentPosition = getCurrentPosition()+1;
            showNoteInTextArea(currentPosition);
            switchNoteColor();
            setCurrentPosition(currentPosition);
        }
    }

    private void showNoteInTextArea(int currentPosition) {
        textArea.setText(noteList.get(currentPosition));
        setCurrentPosition(currentPosition);
    }

    /**
     * Scanner saved lines to list from .txt file until matches
     * delimiter "\\R\\" (double enter)
     */
    private void generateNoteTextFromFile() {
        File file = new File(NOTE_FILE_PATH);
        try {
            scanner = new Scanner((file.getAbsoluteFile()), "cp1250");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        scanner.useDelimiter(Pattern.compile("\\R\\R"));
        while (scanner.hasNext()) {
            noteList.add(scanner.next());
        }
    }

    private int getCurrentPosition() {
        return currentPosition;
    }

    private void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }
}
