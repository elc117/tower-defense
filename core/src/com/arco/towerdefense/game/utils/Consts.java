package com.arco.towerdefense.game.utils;

public class Consts {

    //Game Config
    public static final String TITLE = "Tower Defense";
    public static final int V_WIDTH = 800;
    public static final int V_HEIGHT = 480;

    //Assets
    public static final String TOWER_GLOBULO_BRANCO = "towers/globuloBranco.png";
    public static final String TOWER_GLOBULO_BRANCO2 = "towers/globuloBranco2.png";
    public static final String TOWER_GLOBULO_BRANCO_SELECTION = "towers_selection/globuloBranco.png";
    public static final String TOWER_GLOBULO_BRANCO_SELECTION2 = "towers_selection/globuloBranco2.png";
    public static final String HOME_BUTTON = "menu/HomeButton.png";
    public static final String CONFIG_BUTTON = "menu/config.png";
    public static final String HIGH_CONFIG_BUTTON = "menu/configSelected.png";
    public static final String PLAY_BUTTON = "menu/PlayButton.png";
    public static final String QUIT_BUTTON = "menu/QuitButton.png";
    public static final String HELP_BUTTON = "menu/HelpButton.png";
    public static final String GROUND_GRASS = "ground/grasstop.png";
    public static final String GROUND_DIRT = "ground/dirt.png";
    public static final String GROUND_VEINS = "ground/veins.png";
    public static final String BADLOGIC = "badlogic.jpg";

    //ENEMIES
    public static final String VIRUS_ENEMY = "enemies/generic/virus/virus_animation.png";
    public static final String BACTERIA_ENEMY = "enemies/generic/bacteria/bacteria_animation.png";
    public static final String FUNGUS_ENEMY = "enemies/generic/fungus/fungus_animation.png";
    //public static final String PROTOZOA_ENEMY = "";
    public static final String ENEMIES_JSON = "enemies/enemies.json";

    public static final String TOWERS_JSON = "towers/towers.json";
    public static final String LEVELS_JSON = "levels/levels.json";

    // Attacks
    public static final String ATTACK_BADLOGIC = "badlogic.jpg";

    private Consts(){
        //this prevents even the native class from
        //calling this constructor as well :
        throw new AssertionError();
    }
}
