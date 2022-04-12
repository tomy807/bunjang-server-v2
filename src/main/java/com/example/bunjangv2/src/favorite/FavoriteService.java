package com.example.bunjangv2.src.favorite;

import com.example.bunjangv2.entity.Favorite;
import com.example.bunjangv2.entity.Product;
import com.example.bunjangv2.entity.User;
import com.example.bunjangv2.src.favorite.dto.FavoriteDto;
import com.example.bunjangv2.src.product.ProductRepository;
import com.example.bunjangv2.src.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Transactional
    public String  exchangeFavorite(Long productIdx, User user) {

        Product product = productRepository.findById(productIdx).orElseThrow(() -> new EntityNotFoundException("해당 상품을 찾을수 없습니다."));


        user = userRepository.findByEmail(user.getEmail()).get();
        List<Favorite> favorites = favoriteRepository.findByUser(user);
        boolean removeIf = false;
        for (Favorite favorite : favorites) {
            if (favorite.getProduct().getId().equals(productIdx)) {
                removeIf = true;
                favoriteRepository.delete(favorite);
                favorites.remove(favorite);
                break;
            }
        }
        if (!removeIf) {
            Favorite favorite = new Favorite(user, product);
            favorites.add(favorite);
            favoriteRepository.save(favorite);
            return "찜 목록에 추가하였습니다.";
        }

        return "찜 목록에서 지웠습니다.";
    }

    public List<FavoriteDto> getFavorites(User user) {

        user = userRepository.findByIdx(user.getId());
        return favoriteRepository.findFavorites(user);
    }
}
