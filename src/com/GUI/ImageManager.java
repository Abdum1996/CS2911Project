package com.GUI;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.io.File;

import javax.imageio.ImageIO;

import com.Model.Direction;
import com.Model.GameConstants;
import com.Model.Tile;


/**
*   ImageManager manages the image loading for all the graphics
 */
public class ImageManager {
    /**
    * hash map of all images for tiles
     */
    private HashMap<Tile, BufferedImage> tileImgs;
    /**
    * hash map of all images for boxes
     */
    private HashMap<Integer, BufferedImage> boxImgs;
    
    /**
     * spritesheet object for the player
     */
    private GSpritesheet sheet;
    
    private final int imgWidth = GameConstants.IMAGE_DIMENSION;
    private final int imgHeight = GameConstants.IMAGE_DIMENSION;

    /**
    * folder directory of tile, entity and default image
    * @param tileImageSrc location of tile images
    * @param entityImageSrc location of entity images
    * @param defaultImageSrc location of the default image
     */
    public ImageManager () {
        this.tileImgs = new HashMap<>();
        this.boxImgs = new HashMap<>();
        this.sheet = new GSpritesheet();
    }

    public void loadTileImg (Tile type, String path) {
        BufferedImage tileImage;
        try {
            tileImage = ImageIO.read(new File(path));
            //System.out.println("loaded tile image");

            tileImgs.put(type, tileImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void loadBoxImg (int boxId, String path) {
        BufferedImage boxImage;
        try {
            boxImage = ImageIO.read(new File(path));
            boxImgs.put(boxId, boxImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getTileImg (Tile type) {
        return this.tileImgs.get(type);
    }
    public BufferedImage getBoxImg (int id) {
        return this.boxImgs.get(id);
    }

    public BufferedImage getPlayerImg (Direction dir) {
        return sheet.getNextSprite(dir);
    }
    
    public int getImgHeight () {
        return this.imgHeight;
    }
    public int getImgWidth() {
        return this.imgWidth;
    }
}
