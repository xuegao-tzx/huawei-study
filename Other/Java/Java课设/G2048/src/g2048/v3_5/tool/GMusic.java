package g2048.v3_5.tool;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author Xcl
 * @date 2021/12/24 13:48
 * @package g2048.v3_5
 */
public class GMusic extends Thread {
    Player player;
    String music;

    public GMusic(String file) {
        this.setMusic(file);
    }

    @Override
    public void run() {
        try {
            play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() throws FileNotFoundException, JavaLayerException {
        BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(getMusic()));
        setPlayer(new Player(buffer));
        getPlayer().play();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }
}
