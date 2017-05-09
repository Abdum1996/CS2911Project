package com.GUI;

import com.EntityTypes;
import com.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
*   ImageManager manages the image loading for all the graphics
 */
public class ImageManager {
    /**
    * hash map of all images for tiles
     */
    private HashMap<Tile, BufferedImage> tileImgs;
    /**
    * hash map of all images for entities
     */
    private HashMap<EntityTypes, BufferedImage> entityImgs;

    private final int imgWidth = 64;
    private final int imgHeight = 64;

    /**
    * folder directory of tile, entity and default image
    * @param tileImageSrc location of tile images
    * @param entityImageSrc location of entity images
    * @param defaultImageSrc location of the default image
     */
    public ImageManager () {
        this.tileImgs = new HashMap<>();
        this.entityImgs = new HashMap<>();
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
    public void loadEntityImg (EntityTypes type, String path) {
        BufferedImage tileImage;
        try {
            tileImage = ImageIO.read(new File(path));
            entityImgs.put(type, tileImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getTileImg (Tile type) {
        return this.tileImgs.get(type);
    }
    public BufferedImage getEntityImg (EntityTypes type) {
        return this.entityImgs.get(type);
    }

    public int getImgHeight () {
        return this.imgHeight;
    }
    public int getImgWidth() {
        return this.imgWidth;
    }
}
