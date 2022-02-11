package com.badlogic.mygame.model;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Spritesheet {
    public Texture spriteSheet;
    public TextureRegion[] spriteFrames;

    public Animation<TextureRegion> animation;
    public Animation<TextureRegion> flippedAnimation;

    public Spritesheet(String pathToFile, int eachSpriteWidth, int eachSpriteHeight){
        spriteSheet = new Texture(new FileHandle(pathToFile));

        TextureRegion[][] spriteSheetFrames = TextureRegion.split(spriteSheet, eachSpriteWidth, eachSpriteHeight);

        // counting how many sprites I have:
        int counter = 0;
        for(int row=0;row<spriteSheetFrames.length;row++){
            for(int column=0;column<spriteSheetFrames[row].length; column++){
                counter++;
            }
        }

        spriteFrames = new TextureRegion[counter];

        //reset counter
        counter=0;
        for (TextureRegion[] row:spriteSheetFrames){
            for (TextureRegion sprite:row){
                spriteFrames[counter++] = sprite;
            }
        }
    }

    // returning the animation to the player
    public Animation createAnimation(int startFrame, int lastFrame, float animationSpeed){
        // tell the size of its array
        int counter = (lastFrame + 1) - startFrame;

        TextureRegion[] animationFrames = new TextureRegion[counter];

        // start from lastFrame and decrement it
        for(int index=lastFrame; index>=startFrame; index++){
            animationFrames[--counter] = spriteFrames[index];
        }

        animation = new Animation<TextureRegion>(animationSpeed, animationFrames);
        return animation;
    }

    // flip the animation so it will face the other direction
    public Animation flipAnimation(Animation originalAnimation, boolean flipX, boolean flipY){
        int frameCount = originalAnimation.getKeyFrames().length;
        TextureRegion[] flippedFrames = new TextureRegion[frameCount];

        for(int index=0; index<=frameCount-1; index++){
            flippedFrames[index] = (TextureRegion) (originalAnimation.getKeyFrames())[index];
            // boolean to flip horizontally or vertically
            flippedFrames[index].flip(flipX, flipY);
        }
        flippedAnimation = new Animation<TextureRegion>(originalAnimation.getFrameDuration(), flippedFrames);
        return flippedAnimation;
    }
}
