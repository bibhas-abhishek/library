// Adapter Design Pattern

package designpatterns.adapterpattern;

public class AdapterPattern {

    // Target interface: the interface the client expects to work with
    interface MediaPlayer {
        void play(String filename);
    }

    // Natively compatible: already implements MediaPlayer, so no adapter needed
    static class Mp3Player implements MediaPlayer {
        @Override
        public void play(String filename) {
            System.out.println("Playing MP3 file: " + filename);
        }
    }

    // Adaptee: existing class with an incompatible interface that we want to reuse
    static class VlcPlayer {
        void playVlc(String filename) {
            System.out.println("Playing VLC file: " + filename);
        }
    }

    // Adaptee: another incompatible class
    static class Mp4Player {
        void playMp4(String filename) {
            System.out.println("Playing MP4 file: " + filename);
        }
    }

    // Adapter: wraps VlcPlayer and translates play() to playVlc()
    static class VlcPlayerAdapter implements MediaPlayer {
        private final VlcPlayer vlcPlayer;

        VlcPlayerAdapter(VlcPlayer vlcPlayer) {
            this.vlcPlayer = vlcPlayer;
        }

        @Override
        public void play(String filename) {
            vlcPlayer.playVlc(filename);
        }
    }

    // Adapter: wraps Mp4Player and translates play() to playMp4()
    static class Mp4PlayerAdapter implements MediaPlayer {
        private final Mp4Player mp4Player;

        Mp4PlayerAdapter(Mp4Player mp4Player) {
            this.mp4Player = mp4Player;
        }

        @Override
        public void play(String filename) {
            mp4Player.playMp4(filename);
        }
    }

    public static void main(String[] args) {
        // MP3 implements MediaPlayer directly — no adapter needed
        MediaPlayer mp3 = new Mp3Player();
        mp3.play("song.mp3");

        // VLC and MP4 have incompatible APIs, so adapters bridge the gap
        MediaPlayer vlc = new VlcPlayerAdapter(new VlcPlayer());
        vlc.play("movie.vlc");

        MediaPlayer mp4 = new Mp4PlayerAdapter(new Mp4Player());
        mp4.play("video.mp4");
    }
}
