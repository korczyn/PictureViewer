package com.capgemini.starterkit.javafx.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import com.capgemini.starterkit.javafx.dataprovider.DataProvider;
import com.capgemini.starterkit.javafx.dataprovider.data.PictureVO;
import com.capgemini.starterkit.javafx.picture.model.Picture;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;

public class PictureViewerController {

	@FXML TableView<PictureVO> pictureTable;
	@FXML TableColumn<PictureVO, String> fileNameColumn;
	@FXML Label fileNameLabel;
	@FXML Label fileSizeLabel;
	@FXML ImageView imageArea;
	@FXML Pane pane;
	@FXML Button pickDirButton;
	@FXML TextField dirField;
	@FXML Label stateLabel;

	private final DataProvider dataProvider = DataProvider.INSTANCE;
	private Picture model = new Picture();

	@FXML
	private void initialize() {
		initializeResultTable();
		pictureTable.itemsProperty().bind(model.resultProperty());
	}

	private void initializeResultTable() {
		fileNameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getFileName()));
		pictureTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PictureVO>() {

			@Override
			public void changed(ObservableValue<? extends PictureVO> observable, PictureVO oldValue,
					PictureVO newValue) {
				Task<Void> backgroundTask = new Task<Void>(){

					@Override
					protected Void call() throws Exception {
						/*
						 * REV: modyfikacja kontrolek GUI musi odbywac sie w watku JavaFX
						 */
						setImageOnImageView(newValue);
						return null;
					}
				};
				new Thread(backgroundTask).start();
			}
		});
	}

	private void setImageOnImageView(PictureVO picture){
		if(picture != null){
			File file = new File(picture.getFilePath());
			final Image image2 = new Image(file.toURI().toString());
			/*
			 * REV: te dwa bindy powinny byc zdefiniowane w metodzie initialize()
			 */
			imageArea.fitWidthProperty().bind(pane.widthProperty());
			imageArea.fitHeightProperty().bind(pane.heightProperty());
			imageArea.setImage(image2);
		}
	}

	@FXML
	private void changeDirButtonAction() {
		DirectoryChooser chooser = new DirectoryChooser();
		/*
		 * REV: zadziala tylko na Windowsie
		 */
		chooser.setInitialDirectory(new File("C:\\"));
		File selected = chooser.showDialog(imageArea.getScene().getWindow());

		/*
		 * REV: zmienna 'selected' jest nullem, gdy uzytkownik kliknie Cancel w oknie wyboru katalogu
		 */
		stateLabel.setText(selected.getAbsolutePath());

		Task<Collection<PictureVO>> backgroundTask = new Task<Collection<PictureVO>>() {

			@Override
			protected Collection<PictureVO> call() throws Exception {
				Collection<PictureVO> result = dataProvider.findPictures(selected.getAbsolutePath());
				return result;
			}

			@Override
			protected void succeeded(){
				model.setResult(new ArrayList<PictureVO>(getValue()));
				pictureTable.getSortOrder().clear();
			}
		};
		new Thread(backgroundTask).start();
	}
}
