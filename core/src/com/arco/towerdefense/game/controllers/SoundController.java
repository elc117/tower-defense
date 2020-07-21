package com.arco.towerdefense.game.controllers;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundController {
    float effectsVolume;
    float musicVolume;

    public SoundController() {
        this.effectsVolume = 0.5f;
        this.musicVolume = 0.5f;
    }

    public float getEffectsVolume() {
        return effectsVolume;
    }

    private void setEffectsVolume(float volume) {
        this.effectsVolume = volume;
    }

    public float getMusicVolume() {
        return musicVolume;
    }

    private void setMusicVolume(float volume) {
        this.musicVolume = volume;
    }

    public void turnUpVolume(Music music){
        setMusicVolume(getMusicVolume() + 0.1f);
        music.setVolume(getMusicVolume());
    }

    public void turnDownVolume(Music music){
        setMusicVolume(getMusicVolume() - 0.1f);
        music.setVolume(getMusicVolume());
    }
}
