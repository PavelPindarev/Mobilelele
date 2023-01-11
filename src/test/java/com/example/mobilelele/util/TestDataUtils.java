package com.example.mobilelele.util;

import com.example.mobilelele.model.entity.*;
import com.example.mobilelele.model.enums.CategoryType;
import com.example.mobilelele.model.enums.EngineType;
import com.example.mobilelele.model.enums.RoleType;
import com.example.mobilelele.model.enums.TransmissionType;
import com.example.mobilelele.repository.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Component
public class TestDataUtils {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final OfferRepository offerRepository;
    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;

    public TestDataUtils(UserRepository userRepository,
                         UserRoleRepository userRoleRepository,
                         OfferRepository offerRepository,
                         ModelRepository modelRepository,
                         BrandRepository brandRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.offerRepository = offerRepository;
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
    }

    private void initRoles() {
        if (userRoleRepository.count() == 0) {

            UserRole roleUser = new UserRole()
                    .setRole(RoleType.USER);

            UserRole roleAdmin = new UserRole()
                    .setRole(RoleType.ADMIN);

            userRoleRepository.saveAll(List.of(roleUser, roleAdmin));
        }

    }

    public User createTestAdmin(String email) {
        initRoles();

        User testAdmin = new User()
                .setEmail(email)
                .setRoles((Set<UserRole>) userRoleRepository.findAll())
                .setFirstName("Admin")
                .setLastName("Adminov")
                .setActive(true);

        return userRepository.save(testAdmin);
    }

    public User createTestUser(String email) {
        initRoles();

        User testUser = new User()
                .setEmail(email)
                .setRoles((Set<UserRole>) userRoleRepository.findByRole(RoleType.USER))
                .setFirstName("User")
                .setLastName("Userov")
                .setActive(true);

        return userRepository.save(testUser);
    }

    public Offer createTestOffer(User seller,
                                       Model model) {
        var offerEntity = new Offer().
                setEngine(EngineType.GASOLINE).
                setMileage(100000).
                setPrice(BigDecimal.TEN).
                setDescription("Test description").
                setTransmission(TransmissionType.MANUAL).
                setYear(2000).
                setModel(model).
                setSeller(seller);

        return offerRepository.save(offerEntity);
    }

    public Model createTestModel(Brand brand) {

        Model testModel = new Model()
                .setCategory(CategoryType.CAR)
                .setImageUrl("https://images.com/image.png")
                .setName("Golf")
                .setStartYear(1989)
                .setBrand(brand);

        return modelRepository.save(testModel);
    }

    public Brand createTestBrand() {
        Brand testBrand = new Brand()
                .setName("VW");
        return brandRepository.save(testBrand);
    }

    public void cleanUpDatabase() {
        offerRepository.deleteAll();
        userRepository.deleteAll();
        userRoleRepository.deleteAll();
        modelRepository.deleteAll();
        brandRepository.deleteAll();
    }
}
