package com.arco.towerdefense.game.factories;

import com.arco.towerdefense.game.GameSingleton;
import com.arco.towerdefense.game.entities.EnemyEntity;
import com.arco.towerdefense.game.entities.TowerEntity;
import com.arco.towerdefense.game.utils.Consts;
import com.arco.towerdefense.game.utils.json.EnemyJson;
import com.arco.towerdefense.game.utils.json.TowerJson;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

import java.util.ArrayList;
import java.util.UUID;

public class EnemyFactory {
    ArrayList<EnemyJson> enemiesJson;
    private static final int FRAME_COLS = 1, FRAME_ROWS = 2;
    Animation<TextureRegion> monsterAnimation;

    public EnemyFactory() {
        Json json = new Json();
        enemiesJson = json.fromJson(ArrayList.class, EnemyJson.class, Gdx.files.internal(Consts.ENEMIES_JSON));
        createAnimation();
    }

    public EnemyEntity createById(int id) {
        for (EnemyJson enemyJson: enemiesJson) {
            if (enemyJson.id == id) {
                return create(enemyJson);
            }
        }
        return null;
    }

    public EnemyEntity create(EnemyJson enemyJson) {
        UUID targetID = UUID.randomUUID();
        EnemyEntity enemyEntity = new EnemyEntity(enemyJson.id, enemyJson.speed, enemyJson.skinPath, targetID, monsterAnimation);

        return enemyEntity;
    }

    public void createAnimation() {

        // Load the sprite sheet as a Texture
        Texture provideTxt = new Texture(Gdx.files.internal("enemies/animation_monster.png"));

        // Use the split utility method to create a 2D array of TextureRegions. This is
        // possible because this sprite sheet contains frames of equal size and they are
        // all aligned.
        TextureRegion[][] tmp = TextureRegion.split(provideTxt,
                provideTxt.getWidth() / FRAME_COLS,
                provideTxt.getHeight() / FRAME_ROWS);

        // Place the regions into a 1D array in the correct order, starting from the top
        // left, going across first. The Animation constructor requires a 1D array.
        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }

        // Initialize the Animation with the frame interval and array of frames
        monsterAnimation = new Animation<TextureRegion>(0.5f, walkFrames);
    }
}
