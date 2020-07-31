package com.arco.towerdefense.game.controllers;

import com.arco.towerdefense.game.GameSingleton;
import com.arco.towerdefense.game.entities.TowerEntity;
import com.arco.towerdefense.game.utils.Consts;
import com.arco.towerdefense.game.utils.json.TowerJson;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.util.ArrayList;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class HudController{
    private Stage stage;
    private Skin skin;
    private GroundController groundController;
    private Table table;
    private Image coinHud;
    private Image heartHud;
    private Label waveLabel;
    private Label coinLabel;
    private Label heartLabel;
    private Label towerInfoLabel;
    private Label noMoneyLabel;
    private Image backGroundHud;
    private ArrayList<Button> towerSelections;

    public HudController(SpriteBatch batch, GroundController groundController, OrthographicCamera camera) {
        this.groundController = groundController;
        this.stage = new Stage(new StretchViewport(Consts.V_WIDTH, Consts.V_HEIGHT, camera), batch);
        stage.clear();

        this.towerSelections = new ArrayList<>();

        this.skin = new Skin();
        this.skin.addRegions(GameSingleton.getInstance().getTextureAtlas("towers_selection/towers_select.atlas"));
        this.skin.load(Gdx.files.internal("towers_selection/towers_select.json"));

        this.table = new Table();
        this.table.setFillParent(true);

        initTowers();
        initLabels();
        initImages();

        setTable();

        //stage.addActor(backGroundHud);
        stage.addActor(table);
        stage.addActor(waveLabel);
        stage.addActor(towerInfoLabel);
        stage.addActor(noMoneyLabel);

    }

    public void update(float delta, int waveID, int money, int hearts) {
        waveLabel.setText(String.format("WAVE: %01d", waveID));
        heartLabel.setText(String.format("%01d", hearts));
        coinLabel.setText(String.format("%01d", money));
        stage.act(delta);
        stage.draw();
    }

    private void initImages() {

        this.coinHud = new Image((new Texture("hud/coin.png")));
        this.heartHud = new Image((new Texture("hud/heart.png")));
        //this.backGroundHud = new Image(new Texture("hud/hintbox.png"));
    }

    private void initLabels() {

        Label.LabelStyle labelStyle = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        this.towerInfoLabel = new Label(String.format("TOWER SELECTION", groundController.getLevelHearts()), labelStyle);
        this.towerInfoLabel.setPosition(70, 85);
        this.waveLabel = new Label("WAVE", labelStyle);
        this.waveLabel.setPosition(0, Consts.V_HEIGHT - waveLabel.getHeight());
        this.coinLabel = new Label(String.format("%01d", groundController.getLevelMoney()), labelStyle);
        this.heartLabel = new Label(String.format("%01d", groundController.getLevelHearts()), labelStyle);

        this.noMoneyLabel = new Label("VOCÊ NÃO POSSUI DINHEIRO SUFICIENTE :(", labelStyle);
        noMoneyLabel.setVisible(false);
    }

    private void initTowers() {

        Json json = new Json();
        Array<TowerJson> towersJson = json.fromJson(Array.class, TowerJson.class, Gdx.files.internal(Consts.TOWERS_JSON));

        for (final TowerJson towerJson: towersJson) {
            Button button = new Button(skin, "tower"+String.format("%01d", towerJson.id));
            towerSelections.add(button);

            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {

                    if(groundController.getLevelMoney() - towerJson.price >= 0) {

                        groundController.setHasSelectedTower(true);
                        TowerEntity towerEntity = GameSingleton.getInstance().getTowerFactory().createById(towerJson.id);
                        setTowerEntityToHolder(towerEntity);

                        groundController.setMoney(towerEntity.getPrice());
                    } else {
                        noMoneyLabel.setVisible(true);
                        noMoneyLabel.addAction(sequence( alpha(0), fadeIn(.5f), delay(1f), fadeOut(1.5f)));
                    }
                }

                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    String str1 = new String();
                    str1 = "Name: " + towerJson.name +
                                "  Price: " + String.format("%01d", towerJson.price) +
                                    "  Range: " + String.format("%01d", (int) towerJson.range) +
                                        "  Damange: " + String.format("%01d", towerJson.damage) ;

                    towerInfoLabel.setText(str1);
                    towerInfoLabel.setX(2);
                }

                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    towerInfoLabel.setText("TOWERS SELECTION");
                    towerInfoLabel.setX(70);
                }
            });
        }
    }

    private void setTable() {
        table.align(Align.bottomLeft);
        table.padBottom(10);
        table.padLeft(6);

        for(Button button : towerSelections)
            table.add(button);

        table.add(coinHud).padLeft(64);
        table.add(coinLabel);
        table.add(heartHud).padLeft(64);
        table.add(heartLabel);
    }

    public Stage getStage() {
        return this.stage;
    }

    private void setTowerEntityToHolder(TowerEntity t) {
        groundController.setTowerEntityHolder(t);
        groundController.setSelectedTowerSprite(t.getTexture());
    }

    public void dispose() {
        stage.dispose();
    }

}
