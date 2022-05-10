package com.badlogic.mygame.models.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.mygame.models.player.Inventory;
import com.badlogic.mygame.models.player.Player;

    public class Book extends Item{


        public Book(String name, String description ,String textureUrl) {
            super(name, description, textureUrl);
            isUsable = true;
        }

        public  void use(Player player){

            isUsable = false;
            read(player);

        }

        private void read(Player player){

            player.setGpa(player.getGpa()+ 0.1f );
            player.addXP(10);

            Inventory inventory = player.getInventory();
            inventory.delete(this);

        }


        public Texture getTexture() {
            return  texture;
        }
        public String getName() {
            return name;
        }
        public String getDescription() {
            return description;
        }
        public boolean getIsUsable() {
            return isUsable;
        }

        public String getTextureUrl() {
            return textureUrl;
        }
    }

