package com.badlogic.mygame.model;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.mygame.controller.LevelController;
import com.badlogic.mygame.controller.SpriteController;

import java.util.HashMap;

public class Player extends SpriteController {
    //private HashMap<String, Animation> animations;
    //public static Batch batch;

    // passing al the parameters of the player
    public Player(Vector2 position, int widthEachPlayer, int heightEachPlayer, String player2SpritesRelativePath, float spriteVelocity){
        super(position, widthEachPlayer, heightEachPlayer, player2SpritesRelativePath, spriteVelocity);
        setSpriteAnimations("idleFront",4, 6, 0.25f);
        setSpriteAnimations("idleLeft",7, 9, 0.25f);
        setSpriteAnimations("idleBack",10, 11, 0.25f);
        setSpriteAnimations("idleRight",12, 14, 0.25f);
        setSpriteAnimations("walkFront",15, 19, 0.25f);
        setSpriteAnimations("walkLeft",20, 24, 0.25f);
        setSpriteAnimations("walkRight",25, 29, 0.25f);
        setSpriteAnimations("walkBack",30, 34, 0.25f);
        setCurrentAnimation("idleFront");
        createBox2d();
    }

    public void setMovDir(Vector2 movDir){
        super.setMovDir(movDir);
    }

    public void setSpriteAnimations(String animationName, int startFrame, int lastFrame, float animationSpeed){
        super.setSpriteAnimations(animationName, startFrame, lastFrame, animationSpeed);
    }

    public void setCurrentAnimation(String currentAnimation){
        super.setCurrentAnimation(currentAnimation);
    }

    public void draw(Batch batch){
        super.draw(batch);
    }

    public void update(float deltaTime){
        super.update(deltaTime);
        final double SPEEDTRANSLOC = 0.051; // numbers +/- as 0.03535534 and 0.05 = walking // 0.05656854 and 0.08 = running

        if(movementAngle== 0 && movDir.x == 0 && movDir.y== 0){
            System.out.println("I'm stand................");
            setCurrentAnimation("idleFront");
        }
        if(movementAngle== 0 && movDir.x != 0){
            setCurrentAnimation("walkRight");
            if(movDir.x > SPEEDTRANSLOC){
                System.out.println("I'm RUNNING Right................");
            }
            else{System.out.println("I'm WALKING Right................");}
        }
        if(movementAngle== 180 && movDir.x != 0){
            setCurrentAnimation("walkLeft");
            if(movDir.x < -SPEEDTRANSLOC){
                System.out.println("I'm RUNNING Left................");
            }
            else{System.out.println("I'm WALKING Left................");}
        }
        if(movementAngle== 270 && movDir.y != 0){
            setCurrentAnimation("walkBack");
            if(movDir.y > SPEEDTRANSLOC){
                System.out.println("I'm RUNNING Up................");
            }
            else{System.out.println("I'm WALKING Up................");}
        }
        if(movementAngle== 90 && movDir.y != 0){
            setCurrentAnimation("walkFront");
            if(movDir.y < -SPEEDTRANSLOC){
                System.out.println("I'm RUNNING Down................");
            }
            else{System.out.println("I'm WALKING Down................");}
        }
    }

    // specific for this player
    public void createBox2d(){
        BodyDef bodyDefinition = new BodyDef();
        bodyDefinition.position.set(this.position);

        physicsBody = LevelController.world.createBody(bodyDefinition);
        physicsBody.setUserData(this);

        PolygonShape rectangleShape = new PolygonShape();
        rectangleShape.setAsBox(this.widthEachPlayer/2-1.1f, this.heightEachPlayer/2-1.3f, new Vector2(this.widthEachPlayer/2, this.heightEachPlayer/2+.05f), 0f);

        FixtureDef fixtureDefinition = new FixtureDef();
        fixtureDefinition.shape = rectangleShape;

        physicsBody.createFixture(fixtureDefinition);
    }
}
