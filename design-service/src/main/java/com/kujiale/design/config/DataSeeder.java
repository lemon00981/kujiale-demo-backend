package com.kujiale.design.config;

import com.kujiale.design.entity.HouseType;
import com.kujiale.design.entity.User;
import com.kujiale.design.repository.HouseTypeRepository;
import com.kujiale.design.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 首次启动时写入演示用户与示例户型，让 demo 开箱即用。
 */
@Component
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final HouseTypeRepository houseTypeRepository;

    public DataSeeder(UserRepository userRepository, HouseTypeRepository houseTypeRepository) {
        this.userRepository = userRepository;
        this.houseTypeRepository = houseTypeRepository;
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            User user = new User();
            user.setUsername("demo");
            user.setNickname("演示用户");
            user.setAvatar("");
            userRepository.save(user);
        }

        if (houseTypeRepository.count() == 0) {
            seedHouseType("三室两厅", 90.0, "{\"rooms\":[\"客厅\",\"主卧\",\"次卧\",\"书房\",\"厨房\",\"卫生间\"]}");
            seedHouseType("两室一厅", 70.0, "{\"rooms\":[\"客厅\",\"主卧\",\"次卧\",\"厨房\",\"卫生间\"]}");
            seedHouseType("一室一厅", 45.0, "{\"rooms\":[\"客厅\",\"卧室\",\"厨房\",\"卫生间\"]}");
        }
    }

    private void seedHouseType(String name, double area, String layoutJson) {
        HouseType houseType = new HouseType();
        houseType.setName(name);
        houseType.setArea(area);
        houseType.setLayoutJson(layoutJson);
        houseTypeRepository.save(houseType);
    }
}
