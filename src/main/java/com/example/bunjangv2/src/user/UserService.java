package com.example.bunjangv2.src.user;


import com.example.bunjangv2.config.security.JwtTokenProvider;
import com.example.bunjangv2.entity.Follow;
import com.example.bunjangv2.entity.Order;
import com.example.bunjangv2.entity.Product;
import com.example.bunjangv2.entity.User;
import com.example.bunjangv2.exception.AlreadyExistEmailException;
import com.example.bunjangv2.src.order.OrderRepository;
import com.example.bunjangv2.src.user.dto.LoginDto;
import com.example.bunjangv2.src.user.dto.LoginResDto;
import com.example.bunjangv2.src.user.dto.MyPageDto;
import com.example.bunjangv2.src.user.dto.SignDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public void join(SignDto signDto) {

        if (userRepository.findByEmail(signDto.getEmail()).isPresent()) {
            throw new AlreadyExistEmailException();
        }

        User user = User.builder()
                .name(signDto.getUserName())
                .shopName(signDto.getUserName()+"상점")
                .email(signDto.getEmail())
                .password(passwordEncoder.encode(signDto.getPassword()))
                .phone(signDto.getPhone()).build();
        userRepository.save(user);
    }

    @Transactional
    public LoginResDto login(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail()).orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        String token = jwtTokenProvider.createToken(user.getId(), user.getEmail());

        return new LoginResDto(token, user.getId());
    }


    public MyPageDto.UserInfo getMyPage(User user) {

        user = userRepository.getById(user.getId());
        return new MyPageDto.UserInfo(user);
    }

    public MyPageDto.MyProducts getMyProducts(User user, String status) {

        user = userRepository.getById(user.getId());
        List<Product> products = user.getProducts().stream().filter(product -> product.getSellStatus().equals(status)).collect(Collectors.toList());

        MyPageDto.MyProducts myProducts = new MyPageDto.MyProducts(products,status);

        return myProducts;
    }

    public List<MyPageDto.MyFollower> getMyFollowers(User user) {
        user = userRepository.getById(user.getId());
        List<Follow> followers = user.getFollowers();
        List<MyPageDto.MyFollower> myFollowerList = new ArrayList<>();
        for (Follow follower : followers) {
            User followerUser = follower.getFromUser();
            myFollowerList.add(new MyPageDto.MyFollower(followerUser));
        }
        return myFollowerList;
    }

    public List<MyPageDto.MyFollowing> getMyFollowings(User user) {
        user = userRepository.getById(user.getId());
        List<Follow> followings = user.getFollowings();
        List<MyPageDto.MyFollowing> myFollowingList = new ArrayList<>();
        for (Follow following : followings) {
            User followingUser = following.getToUser();
            myFollowingList.add(new MyPageDto.MyFollowing(followingUser));
        }
        return myFollowingList;
    }

    public List<MyPageDto.MyPurchaseOrder> getPurchaseList(User user) {
        user = userRepository.getById(user.getId());
        List<Order> orders = orderRepository.findByUser(user);
//        List<MyPageDto.MyPurchaseOrder> purchaseList = orderRepository.findMyPurchaseList(user.getId());
//        return purchaseList;
        return orders.stream().map(MyPageDto.MyPurchaseOrder::new).collect(Collectors.toList());
    }

    public List<MyPageDto.MySellOrder> getSellList(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("유저를 찾을수 없습니다."));
        List<Order> sellOrders = orderRepository.findByProduct_User(user);

        return sellOrders.stream().map(MyPageDto.MySellOrder::new).collect(Collectors.toList());
    }
}
