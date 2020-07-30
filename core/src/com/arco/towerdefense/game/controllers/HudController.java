package com.arco.towerdefense.game.controllers;

import com.arco.towerdefense.game.GameSingleton;
import com.arco.towerdefense.game.entities.TowerEntity;
import com.arco.towerdefense.game.utils.Consts;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.util.ArrayList;

public class HudController extends InputAdapter{
    private Stage stage;
    private Skin skin;
    private GroundController groundController;
    private Button tower1Select, tower2Select, tower3Select, tower4Select;
    private Table table;
    private Image baseHud;

    public HudController(GroundController groundController, OrthographicCamera camera) {
        this.groundController = groundController;
        this.stage = new Stage(new StretchViewport(Consts.V_WIDTH, Consts.V_HEIGHT, camera));
        stage.clear();

        this.skin = new Skin();
        this.skin.addRegions(GameSingleton.getInstance().getTextureAtlas("towers_selection/towers_select.atlas"));
        this.skin.load(Gdx.files.internal("towers_selection/towers_select.json"));

        this.baseHud = new Image( new Texture(Gdx.files.internal("hud/HUDbase.png")));

        this.table = new Table(skin);
        //this.table.setBounds(0, 0, stage.getWidth(), stage.getHeight());
        this.table.setFillParent(true);

        this.tower1Select = new Button(skin, "tower1");
        this.tower2Select = new Button(skin, "tower2");
        this.tower3Select = new Button(skin, "tower3");
        this.tower4Select = new Button(skin, "tower4");

        table.align(Align.bottomLeft);
        table.add(tower1Select);
        table.add(tower2Select);
        table.add(tower3Select);
        table.add(tower4Select);
        table.add(baseHud);
        //table.add(baseHud).setActorHeight();

        stage.addActor(table);

        towersListeners();

    }

    public void update(float delta) {
        Gdx.input.setInputProcessor(stage);
        stage.act(delta);
        stage.draw();
    }

    public void towersListeners() {
        tower1Select.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                groundController.setHasSelectedTower(true);
                TowerEntity towerEntity = GameSingleton.getInstance().getTowerFactory().createById(1);
                setTowerEntityToHolder(towerEntity);
            }
        });
        tower2Select.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                groundController.setHasSelectedTower(true);
                TowerEntity towerEntity = GameSingleton.getInstance().getTowerFactory().createById(2);
                setTowerEntityToHolder(towerEntity);
            }
        });
        tower3Select.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                groundController.setHasSelectedTower(true);
                TowerEntity towerEntity = GameSingleton.getInstance().getTowerFactory().createById(3);
                setTowerEntityToHolder(towerEntity);
            }
        });
        tower4Select.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                groundController.setHasSelectedTower(true);
                TowerEntity towerEntity = GameSingleton.getInstance().getTowerFactory().createById(4);
                setTowerEntityToHolder(towerEntity);
            }
        });
    }

    private void setTowerEntityToHolder(TowerEntity t) {
        groundController.setTowerEntityHolder(t);
        groundController.setSelectedTowerSprite(t.getTexture());
    }

    public void dispose() {
        stage.dispose();
    }

}
