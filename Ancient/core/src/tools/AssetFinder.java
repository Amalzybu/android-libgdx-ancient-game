package tools;

import java.util.Iterator;
import java.util.Map;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

public class AssetFinder {

	
    private AssetManager assetManager;
    private FileHandleResolver resolver;
    Iterator it;

    public class AssetDescriptor {
        public String folder;
        public Class<?> assetType;

        public AssetDescriptor(String folder, Class<?> assetType) {
            this.folder = folder;
            this.assetType = assetType;
        }
    }

    private Array<AssetDescriptor> assets = new Array<AssetDescriptor>();

    public AssetFinder(AssetManager assetManager, FileHandleResolver resolver) {
        this.assetManager = assetManager;
        this.resolver = resolver;
       
            
       
      //  assets.add(new AssetDescriptor("music", Music.class));
       // assets.add(new AssetDescriptor("sound", Sound.class)); // You could remove all but this one
      //  assets.add(new AssetDescriptor("skin", Skin.class));
      //  assets.add(new AssetDescriptor("texture", Texture.class));
      //  assets.add(new AssetDescriptor("atlas", TextureAtlas.class));
       // assets.add(new AssetDescriptor("font", BitmapFont.class));
      //  assets.add(new AssetDescriptor("freetype", FreeTypeFontGenerator.class));
       // assets.add(new AssetDescriptor("effect", ParticleEffect.class));
       // assets.add(new AssetDescriptor("pixmap", Pixmap.class));
      //  assets.add(new AssetDescriptor("region", PolygonRegion.class));
       // assets.add(new AssetDescriptor("model", Model.class));
        //assets.add(new AssetDescriptor("level", TiledMap.class));
    }

    public void loadText(String m) {
    	
    	  assets.add(new AssetDescriptor(m, Texture.class));
    	
        for (AssetDescriptor descriptor : assets) {
            FileHandle folder = resolver.resolve("").child(descriptor.folder);
            if (!folder.exists()) {
            	System.out.println("not found");
                continue;
            }
            else
            {
            	System.out.println("found");
            }

            for (FileHandle asset : folder.list()) {
            	 System.out.println(asset.path()+"  path");
                assetManager.load(asset.path(), descriptor.assetType);
            }
        }
    }
    public void loadMsc(String m) {
    	
  	  assets.add(new AssetDescriptor(m, Music.class));
  	
      for (AssetDescriptor descriptor : assets) {
          FileHandle folder = resolver.resolve("").child(descriptor.folder);
          if (!folder.exists()) {
          	System.out.println("not found");
              continue;
          }
          else
          {
          	System.out.println("found");
          }

          for (FileHandle asset : folder.list()) {
          	 System.out.println(asset.path()+"  path");
              assetManager.load(asset.path(), descriptor.assetType);
          }
      }
  }
    
    
    
   
}