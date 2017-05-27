package hu.bence.jatek.view;

import hu.bence.jatek.model.Player;
import hu.bence.jatek.service.ServiceLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * A játékos által elért vagy még feloldásra váró mérföldkövek megjelenítéséért felelős osztály.
 * 
 * @author erosbencee
 *
 */
public class MilestonesOverviewController {

	@FXML
	ImageView firstAch = new ImageView();
	 
	@FXML
	ImageView hundredAch = new ImageView();
	
	@FXML
	ImageView historyAch = new ImageView();
	
	@FXML
	ImageView scienceAch = new ImageView();
	
	@FXML
	Label firstAchLabel = new Label();
	
	@FXML
	Label hundredAchLabel = new Label();
	
	@FXML
	Label historyAchLabel = new Label();
	
	@FXML
	Label scienceAchLabel = new Label();
	
	private ServiceLoader categoryLoader = new ServiceLoader();
	
	Player player = categoryLoader.getThePlayers().get(1);
	
	@FXML
	private void initialize() {

		if(player.hasThePlayerAnsweredHisFirstQuestion()) {
			firstAch.setImage(new Image("/images/milestone_first.png"));
			firstAchLabel.setText("AZ ELSŐ 5 PONT\n(MEGSZEREZVE)");
			firstAchLabel.setTextFill(Color.ORANGE);
		} else {
			firstAch.setImage(new Image("/images/milestone_unlocked_first.png"));
			firstAchLabel.setText("AZ ELSŐ 5 PONT\n(Válaszolj meg egy kérdést!)");
		}
		
		if(player.hasThePlayerReachedHundredPoints()) {
			hundredAch.setImage(new Image("/images/milestone_hundred.png"));
			hundredAchLabel.setText("SZÁZ PONT!\n(MEGSZEREZVE)");
			hundredAchLabel.setTextFill(Color.ORANGE);
		} else {
			hundredAch.setImage(new Image("/images/milestone_unlocked_hundred.png"));
			hundredAchLabel.setText("SZÁZ PONT!\n(Gyűjts össze 100 pontot!)");
		}
		
		if(player.hasThePlayerReachedNinetyPointFromHistory()) {
			historyAch.setImage(new Image("/images/milestone_history.png"));
			historyAchLabel.setText("A TÖRTÉNELEM SZAKÉRTŐJE\n(MEGSZEREZVE)");
			historyAchLabel.setTextFill(Color.ORANGE);
		} else {
			historyAch.setImage(new Image("/images/milestone_unlocked_history.png"));
			historyAchLabel.setText("A TÖRTÉNELEM SZAKÉRTŐJE\n(Gyűjts össze 90 pontot\ntörténelmi kérdésekből!)");
		}
		
		if(player.hasThePlayerReachedNinetyPointFromScience()) {
			scienceAch.setImage(new Image("/images/milestone_science.png"));
			scienceAchLabel.setText("A TERMÉSZET SZAKÉRTŐJE\n(MEGSZEREZVE)");
			scienceAchLabel.setTextFill(Color.ORANGE);
		} else {
			scienceAch.setImage(new Image("/images/milestone_unlocked_science.png"));
			scienceAchLabel.setText("A TERMÉSZET SZAKÉRTŐJE\n(Gyűjts össze 90 pontot\ntermészettudományos kérdésekből!)");
		}
	}	
	
}
