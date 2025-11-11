package it.unibo.mvc.view;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.api.DrawResult;

/**
 * Command line {@link DrawNumberView} implementation.
 */
public class DrawNumberStandardOutputView implements DrawNumberView{

    private static final String NEW_GAME = ": a new game starts!";

    private DrawNumberController controller;

    public DrawNumberStandardOutputView() {
        
    }

    @Override
    public void setController(DrawNumberController observer) {
        this.controller = observer;
    }

    @Override
    public void start() {
        
    }

    @Override
    public void result(DrawResult res) {
        switch (res) {
            case YOURS_HIGH, YOURS_LOW -> {
                System.out.println(res.getDescription());
                return;
            }
            case YOU_WON, YOU_LOST -> System.out.println(res.getDescription() + NEW_GAME);
        }
        controller.resetGame();
    }

}