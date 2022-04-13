package com.example.bunjangv2.src;

import com.example.bunjangv2.entity.*;
import com.example.bunjangv2.src.product.dto.ProductPostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
    }
    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {
            User member1 = createMember("user1", "user1@naver.com", "test1234", "01033245696");
            em.persist(member1);

            User member2 = createMember("user2", "user2@naver.com", "test1234", "01033245696");
            em.persist(member2);

            User member3 = createMember("user3", "user3@naver.com", "test1234", "01033245696");
            em.persist(member3);

            CategoryLarge categoryLarge = new CategoryLarge();
            categoryLarge.setCategoryLargeName("옷");
            em.persist(categoryLarge);

            CategoryMiddle categoryMiddle = new CategoryMiddle();
            categoryMiddle.setCategoryLarge(categoryLarge);
            categoryMiddle.setCategoryMiddleName("패딩");
            em.persist(categoryMiddle);

            CategorySmall categorySmall = new CategorySmall();
            categorySmall.setCategoryMiddle(categoryMiddle);
            categorySmall.setCategorySmallName("아디다스패딩");
            em.persist(categorySmall);

//            Product product1 = Product.createProduct(new ProductPostDto("다시팝니다", 1L, 1L, 1L, 10000, "안녕하세요", 1, "USED", "NOTEXCHANGE", "SECURE"), categoryLarge, categoryMiddle, categorySmall, member1, "지역정보없음");
//            em.persist(product1);

        }

        private User createMember(String name, String email, String password,
                                    String phone) {
            User member = new User();
            member.setName(name);
            member.setShopName(name + "상점");
            member.setEmail(email);
            member.setPassword(password);
            member.setPhone(phone);
            return member;
        }

        private CategoryLarge createCategoryLarge() {
            CategoryLarge categoryLarge = new CategoryLarge();
            categoryLarge.setCategoryLargeName("옷");
            return categoryLarge;
        }

        private CategoryMiddle createcategoryMiddle() {
            CategoryMiddle categoryMiddle = new CategoryMiddle();
            categoryMiddle.setCategoryLarge(createCategoryLarge());
            categoryMiddle.setCategoryMiddleName("패딩");
            return categoryMiddle;
        }

        private CategorySmall CreateCategorySmall() {
            CategorySmall categorySmall = new CategorySmall();
            categorySmall.setCategoryMiddle(createcategoryMiddle());
            categorySmall.setCategorySmallName("아디다스패딩");
            return categorySmall;
        }

        private Product createProduct(User user) {
            Product product = new Product();
            CategorySmall categorySmall = CreateCategorySmall();
            product.setUser(user);
            product.setTitle("다시팝니다");
            product.setCategoryLarge(categorySmall.getCategoryMiddle().getCategoryLarge());
            product.setCategoryMiddle(categorySmall.getCategoryMiddle());
            product.setCategorySmall(categorySmall);
            product.setPrice(10000);
            product.setIntroduction("qwdqwd");
            product.setQuantity(1);
            product.setProductStatus("USED");
            product.setExchangePossible("NOTEXCHANGE");
            product.setSecurePayment("SECURE");
            return product;
        }


    }
}