package com.arco.towerdefense.game.layouts.wrappers;

import com.arco.towerdefense.game.layouts.interfaces.LayoutListener;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class LayoutWrapper {
    LayoutListener listener;
    public Sprite sprite;
    public Sprite hoverSprite;

    public LayoutWrapper(Sprite sprite, LayoutListener listener) {
        this.listener = listener;
        this.sprite = sprite;
    }

    public void setOnHoverSprite(Sprite sprite) {
        hoverSprite = sprite;
    }

    public void triggerOnClick(){
        this.listener.onClick(this);
    }

    public void triggerOnHover(){
        this.listener.onHover(this);
    }
}
