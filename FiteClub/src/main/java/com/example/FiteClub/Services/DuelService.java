package com.example.FiteClub.Services;

import com.example.FiteClub.Category;
import com.example.FiteClub.Security.Repositories.UserRepository;
import com.example.FiteClub.dto.DuelDTO;
import com.example.FiteClub.dto.DuelResponseDTO;
import com.example.FiteClub.models.Card;
import com.example.FiteClub.models.Duel;
import com.example.FiteClub.repos.CardRepo;
import com.example.FiteClub.repos.DuelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.example.FiteClub.models.Card.getLineByCategory;

@Service
public class DuelService {

    @Autowired
    private CardRepo cardRepo;

    @Autowired
    private DuelRepo duelRepo;

    @Autowired
    private UserRepository userRepo;

    private final Category[] categories = Category.values();

    public ResponseEntity<DuelResponseDTO> getOrCreateDuel() {
        Duel duel = duelRepo.findRandomUnshownDuel();

        if (duel == null) {
            duel = createDuel(); // создаём только если вообще нет не показанных
        }

        duel.setShown(true); // помечаем, что показали
        duelRepo.save(duel); // сохраняем изменение

        DuelResponseDTO response = new DuelResponseDTO(
                duel.getId(),
                duel.getCategory().toString(),
                getLineByCategory(duel.getCard1(), duel.getCategory().toString()),
                duel.getCard1().getUser().getUsername(),
                getLineByCategory(duel.getCard2(), duel.getCategory().toString()),
                duel.getCard2().getUser().getUsername()
        );

        return ResponseEntity.ok(response);
    }

    public Duel createDuel() {
        Category category = categories[(int) (Math.random() * categories.length)];

        Card card1 = cardRepo.findRandomCardByCategory(category.name());
        Card card2;

        do {
            card2 = cardRepo.findRandomCardByCategory(category.name());
        } while (card2 == null || card1.getId().equals(card2.getId()));

        Duel duel = new Duel();
        duel.setCategory(category);
        duel.setCard1(card1);
        duel.setCard2(card2);
        duel.setShown(false);

        return duelRepo.save(duel);
    }
}
