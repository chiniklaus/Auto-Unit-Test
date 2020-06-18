package cs3500.marblesolitaire;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;

import java.io.InputStreamReader;

public class Runner {
    public static void main(String[] args) {
        MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(
                new InputStreamReader(System.in), System.out
        );
        MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
        controller.playGame(model);
    }
}
