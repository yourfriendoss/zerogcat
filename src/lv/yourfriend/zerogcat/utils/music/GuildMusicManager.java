package lv.yourfriend.zerogcat.utils.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class GuildMusicManager {
    public AudioPlayer audioPlayer;
    public TrackScheduler scheduler;

    private AudioPlayerSendHandler sendHandler;

    public GuildMusicManager(AudioPlayerManager manager, TextChannel channel) {
        this.audioPlayer = manager.createPlayer();
        this.scheduler = new TrackScheduler(this.audioPlayer, channel);
        this.audioPlayer.addListener(this.scheduler);
        this.sendHandler = new AudioPlayerSendHandler(this.audioPlayer);
    }

    public AudioPlayerSendHandler getSendHandler() {
        return sendHandler;
    }
}