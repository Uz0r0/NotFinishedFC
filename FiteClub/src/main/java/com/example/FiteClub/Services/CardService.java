package com.example.FiteClub.Services;

import com.example.FiteClub.models.Card;
import com.example.FiteClub.repos.CardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    @Autowired
    private CardRepo cardRepo;

    public void like(Card card) {
        card.setLikes(card.getLikes() + 1);
        cardRepo.save(card);
    }
}
