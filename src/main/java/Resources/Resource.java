package Resources;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import java.io.File;
import java.io.IOException;

public class Resource {

    private Texture texture = null;
    private File file;

    public Resource(String file_name) {
        this.file = new File(file_name);
    }

    public Texture getTexture() {
        if (texture == null) {
            try {
                texture = TextureIO.newTexture(file, true);
            } catch (IOException e) {
                System.out.println("Файл текстуры не найден");
                throw new RuntimeException(e);
            }
        }
        return texture;
    }

    public String getFileName() {
        return  file.getName();
    }
}