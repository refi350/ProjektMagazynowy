package com.warehouse.project.data;

import com.warehouse.project.repository.CommodityRepository;
import com.warehouse.project.repository.WarehouseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(WarehouseRepository repository, CommodityRepository commodityRepository) {
        Address address = new Address("Lipowa", "17/19", 34, "Częstochowa", "42-216");
        byte [] bitki = new byte[] { (byte)0xe0, 0x4f, (byte)0xd0,
                0x20, (byte)0xea, 0x3a, 0x69, 0x10, (byte)0xa2, (byte)0xd8, 0x08, 0x00, 0x2b,
                0x30, 0x30, (byte)0x9d };
        Owner owner = new Owner("Monika", "monikatl@interia.pl");
        List<Commodity> commodityList = List.of(new Commodity("Samochod"), new Commodity("TY"), new Commodity("Pomidor"));
        Warehouse warehouse = new Warehouse("Diamentowy", "rty56tgfrtedrwd", address, bitki, "Czerwony", owner, commodityList);
//        try{
//            warehouse.setCommodities(commodityList);
//            System.out.println(commodityList.get(0).getName());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        Commodity commodity = new Commodity("Coś");
        warehouse.setCommodities(List.of(commodity));
        commodity.setWarehouse(warehouse);

        return args -> {
            log.info("Preloading " + repository.save(warehouse));
            log.info("Preloading " + commodityRepository.save(commodity));
        };
    }
}