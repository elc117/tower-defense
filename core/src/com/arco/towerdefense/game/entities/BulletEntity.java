package com.arco.towerdefense.game.entities;

import com.arco.towerdefense.game.GameSingleton;
import com.arco.towerdefense.game.utils.Utils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class BulletEntity extends Entity {
    private float speed;
    private int damage;
    private boolean shouldRemove;
    private EnemyEntity target;
    Animation<TextureAtlas.AtlasRegion> animation;
    private float stateTime;
    private boolean hasAnimatedSpawn;
    private boolean hasHitTarget;
    private Vector2 originPos;

    private Animation<TextureAtlas.AtlasRegion> spawnAnimations;
    private Animation<TextureAtlas.AtlasRegion> shotAnimations;
    private Animation<TextureAtlas.AtlasRegion> hitAnimations;

    public BulletEntity(String texturePath, String animationAtlasPath, float x, float y, float speed, int damage, EnemyEntity target) {
        super(new Sprite(GameSingleton.getInstance().getTexture(texturePath)), x, y);

        super.setSpriteSizeToScale();

        this.originPos = new Vector2(getScaledX(), getScaledY());

        initAnimationVariables(animationAtlasPath);

        this.stateTime = 0f;
        this.speed = speed;
        this.damage = damage;
        this.shouldRemove = false;
        this.hasAnimatedSpawn = false;
        this.hasHitTarget = false;
        this.target = target;
    }

    private void initAnimationVariables(String animationAtlasPath) {
        TextureAtlas animationAtlas = GameSingleton.getInstance().getTextureAtlas(animationAtlasPath);

        Array<TextureAtlas.AtlasRegion> spawnRegions = animationAtlas.findRegions("spawn");
        Array<TextureAtlas.AtlasRegion> shotRegions = animationAtlas.findRegions("shot");
        Array<TextureAtlas.AtlasRegion> hitRegions = animationAtlas.findRegions("hit");

        this.spawnAnimations = spawnRegions == null ? null : new Animation<>(0.2f / spawnRegions.size, spawnRegions);

        this.shotAnimations = shotRegions == null ? null : new Animation<>(0.01f / shotRegions.size, shotRegions);

        this.hitAnimations = hitRegions == null ? null : new Animation<>(0.3f/ hitRegions.size, hitRegions);
    }

    public void update(float delta) {
        x += adjustDeltaXAxis(delta) * speed;
        y += adjustDeltaYAxis(delta) * speed;

        if (isHittingTarget()) {
            performTargetHit();
        }

        if(!Utils.isInsideScreen(getScaledX(), getScaledY())) {
            shouldRemove = true;
        }
    }

    private void performTargetHit() {
        this.hasHitTarget = true;
        target.performHit(this.damage);
    }

    public boolean isHittingTarget() {
        return Intersector.overlaps(super.getEntityRect(), target.getEntityRect());
    }

    private float adjustDeltaXAxis(float delta) {
        if (x > target.x) {
            return -delta;
        }

        return delta;
    }

    private float adjustDeltaYAxis(float delta) {
        if (y > target.y) {
            return -delta;
        }

        return delta;
    }

    public boolean shouldRemove() {
        return shouldRemove;
    }

    private void drawSpawnBullet(SpriteBatch batch) {
        if (!hasAnimatedSpawn && (spawnAnimations == null || spawnAnimations.isAnimationFinished(stateTime))) {
            hasAnimatedSpawn = true;
            return;
        }

        // We do not use the super.sprite bacause we can have spawn-animation and shot-animation at the same time, so we need
        // another one
        Sprite spawnBulletFrame = new Sprite(spawnAnimations.getKeyFrame(stateTime, false));

        spawnBulletFrame.setSize(super.scale, super.scale);
        spawnBulletFrame.setPosition(originPos.x, originPos.y);

        spawnBulletFrame.draw(batch);
    }

    private void drawBulletOrHit(SpriteBatch batch) {
        Sprite currentFrame;

        if (!hasHitTarget) {
            currentFrame = new Sprite(shotAnimations.getKeyFrame(stateTime, true));
        } else if (hitAnimations != null && !hitAnimations.isAnimationFinished(stateTime)) {
            currentFrame = new Sprite(hitAnimations.getKeyFrame(stateTime, false));
        } else {
            shouldRemove = true;
            return;
        }

        adjustSpriteRotation(currentFrame);
        this.sprite = currentFrame;
        super.setSpriteSizeToScale();

        super.draw(batch); // This call will adjust the sprite position
    }

    private void adjustSpriteRotation(Sprite sprite) {
        if (sprite == null) return;

        // Here we kinda translate the coordinate system to the originPos so the angle is related to it
        Vector2 u = new Vector2(target.getScaledX() - originPos.x, target.getScaledY() - originPos.y);

        float degrees = u.angle();

        sprite.setRotation(degrees);
    }

    public void draw(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime();

        // TODO: MAKE BETTER SHOT TRAJECTORY

        drawSpawnBullet(batch);
        drawBulletOrHit(batch);
    }
}
