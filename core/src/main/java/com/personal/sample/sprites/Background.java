package com.personal.sample.sprites;

import com.badlogic.gdx.graphics.Texture;

public class Background {
    private Texture grassTexture;
    private Texture roadTexture;
    private Texture waterTexture;
    private Texture blackbackground;
    private Texture TrainTrack;
    //private int[] map;


    public Texture getBlackbackground() {
        return blackbackground;
    }

    public Background() {
        grassTexture = new Texture("grass.jpeg");
        roadTexture = new Texture("road.jpeg");
        waterTexture = new Texture("water.jpeg");
        blackbackground = new Texture("background.jpg");
        TrainTrack = new Texture("trainTrack.jpeg");

        // Example map, where 0 is grass, 1 is road, 2 is water
        //map = new int[]{0, 0, 1, 2, 1, 2};
    }

    public Texture getGrassTexture() {
        return grassTexture;
    }

    public Texture getRoadTexture() {
        return roadTexture;
    }

    public Texture getWaterTexture() {
        return waterTexture;
    }

    public Texture getTrainTrackTexture(){return TrainTrack;}
}
