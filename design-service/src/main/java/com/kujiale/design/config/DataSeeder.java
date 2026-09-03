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
            seedHouseType("三室两厅", 90.0, "{\"rooms\":[{\"name\":\"客厅\",\"x\":0,\"z\":0,\"w\":4,\"d\":3},{\"name\":\"厨房\",\"x\":4,\"z\":0,\"w\":2,\"d\":3},{\"name\":\"卫生间\",\"x\":6,\"z\":0,\"w\":2,\"d\":3},{\"name\":\"主卧\",\"x\":0,\"z\":3,\"w\":2.7,\"d\":3},{\"name\":\"次卧\",\"x\":2.7,\"z\":3,\"w\":2.7,\"d\":3},{\"name\":\"书房\",\"x\":5.4,\"z\":3,\"w\":2.6,\"d\":3}]}");
            seedHouseType("两室一厅", 70.0, "{\"rooms\":[{\"name\":\"客厅\",\"x\":0,\"z\":0,\"w\":4,\"d\":3},{\"name\":\"厨房\",\"x\":4,\"z\":0,\"w\":2.5,\"d\":3},{\"name\":\"卫生间\",\"x\":6.5,\"z\":0,\"w\":1.5,\"d\":3},{\"name\":\"主卧\",\"x\":0,\"z\":3,\"w\":4,\"d\":3},{\"name\":\"次卧\",\"x\":4,\"z\":3,\"w\":4,\"d\":3}]}");
            seedHouseType("一室一厅", 45.0, "{\"rooms\":[{\"name\":\"客厅\",\"x\":0,\"z\":0,\"w\":5,\"d\":3.5},{\"name\":\"卧室\",\"x\":0,\"z\":3.5,\"w\":3.5,\"d\":2.5},{\"name\":\"厨房\",\"x\":5,\"z\":0,\"w\":3,\"d\":3},{\"name\":\"卫生间\",\"x\":3.5,\"z\":3.5,\"w\":2,\"d\":2.5}]}");
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
