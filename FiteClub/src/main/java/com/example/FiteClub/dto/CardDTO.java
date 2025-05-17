package com.example.FiteClub.dto;

import com.example.FiteClub.models.Card;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardDTO {
    private String music;
    private String musician;
    private String actor;
    private String anime;
    private String film;
    private String meme;

    public CardDTO(String music, String musician, String actor, String anime, String film, String meme) {
        this.music = music;
        this.musician = musician;
        this.actor = actor;
        this.anime = anime;
        this.film = film;
        this.meme = meme;
    }
    public CardDTO() {}
    public CardDTO(Card card){
        this.music = card.getMusic();
        this.musician = card.getMusician();
        this.actor = card.getActor();
        this.anime = card.getAnime();
        this.film = card.getFilm();
        this.meme = card.getMeme();
    }

}
