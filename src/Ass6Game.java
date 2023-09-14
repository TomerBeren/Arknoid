// 209625946 Tomer Berenstein

import animation.AnimationRunner;
import biuoop.GUI;
import biuoop.Sleeper;
import game.GameFlow;
import game.LevelInformation;
import game.levels.DirectHit;
import game.levels.Green3;
import game.levels.WideEasy;

import java.util.ArrayList;
import java.util.List;


/**
 * Ass6Game is the main class to run the game.
 *
 * @author Tomer Berenstein tomerbrotem@gmail.com
 * @version 19.0.2
 * @since 2023-01-17
 */
public class Ass6Game {
    private static final int GUI_WIDTH = 800;
    private static final int GUI_HEIGHT = 600;
    private static final int FPS = 60;
    private static final int MAX_LEVEL = 3;
    public static final String TITLE = "Arkanoid";

    /**
     * The main method to execute the game.
     *
     * @param args Command line arguments (unused)
     */
    public static void main(String[] args) {
        GUI gui = new GUI(TITLE, GUI_WIDTH, GUI_HEIGHT);
        AnimationRunner runner = new AnimationRunner(gui, FPS, new Sleeper());
        GameFlow gameFlow = new GameFlow(runner, gui.getKeyboardSensor());

        List<LevelInformation> allLevels = new ArrayList<>();
        allLevels.add(new DirectHit());
        allLevels.add(new WideEasy());
        allLevels.add(new Green3());

        // If no arguments are provided, run all levels
        if (args.length == 0 || args[0].equals("${args}")) {
            gameFlow.runLevels(allLevels);
        } else {
            // If arguments are provided, run the specified levels
            List<LevelInformation> selectedLevels = new ArrayList<>();
            for (String arg : args) {
                try {
                    int levelNumber = Integer.parseInt(arg);
                    // Make sure the level number is within the range
                    if (levelNumber >= 1 && levelNumber <= MAX_LEVEL) {
                        selectedLevels.add(allLevels.get(levelNumber - 1));
                    }
                } catch (NumberFormatException e) {
                    // Ignore any argument which is not a number
                }
            }
            if (selectedLevels.size() != 0) {
                gameFlow.runLevels(selectedLevels);
            }

        }
        System.exit(0);
    }
}
