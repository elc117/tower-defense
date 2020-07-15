package com.arco.towerdefense.game.layouts.interfaces;

import com.arco.towerdefense.game.layouts.wrappers.LayoutWrapper;

public interface LayoutListener {
    void onClick(LayoutWrapper layoutWrapper);
    void onHover(LayoutWrapper layoutWrapper);
}
