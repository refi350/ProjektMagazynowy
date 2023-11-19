package com.example.warehousespring.data;

import com.example.warehousespring.repository.*;
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
    CommandLineRunner initDatabase(
            WarehouseRepository repository,
            CommodityRepository commodityRepository,
            ContractorRepository contractorRepository,
            StoreActionRepository storeActionRepository,
            ActionCommodityRepository actionCommodityRepository,
            OrderRepository orderRepository) {
//        Address address = new Address("Lipowa", "17/19", 34, "Częstochowa", "42-216");
//        byte [] bitki = new byte[] { (byte)0xe0, 0x4f, (byte)0xd0,
//                0x20, (byte)0xea, 0x3a, 0x69, 0x10, (byte)0xa2, (byte)0xd8, 0x08, 0x00, 0x2b,
//                0x30, 0x30, (byte)0x9d };
//        Owner owner = new Owner("Monika", "monikatl@interia.pl");
//        List<Commodity> commodityList = List.of(new Commodity("Samochod"), new Commodity("TY"), new Commodity("Pomidor"));
//        Warehouse warehouse = new Warehouse("Rubinowy", "123", address, bitki, "Czerwony", owner, commodityList);
//
//        Commodity commodity = new Commodity("Coś");
//        warehouse.setCommodities(List.of(commodity));
//        commodity.setWarehouse(warehouse);
//
//        Contractor contractor = new Contractor();
//        warehouse.contractors = List.of(contractor);
//        contractor.setWarehouse(warehouse);
//
//        ActionCommodity actionCommodity = new ActionCommodity(commodity, 10);
//
//
//
//        //StoreAction action = new Receipt(LocalDateTime.now(), List.of(actionCommodity), contractor , 14L);
//        //warehouse.setStore_actions(List.of(action));
//        //action.setWarehouse(warehouse);
//
//        Order order = new Order();
//        StoreAction release = new Release(LocalDateTime.now(), List.of(actionCommodity), contractor , 16L);
//        release.setWarehouse(warehouse);
//        warehouse.setStore_actions(List.of(release));
//        ((Release) release).setOrder(order);
//        order.setRelease((Release) release);


        return args -> {
            //log.info("Preloading " + repository.save(warehouse));
            //log.info("Preloading " + commodityRepository.save(commodity));
            //log.info("Preloading " + contractorRepository.save(contractor));
            //log.info("Preloading " + storeActionRepository.save(action));
            //log.info("Preloading " + actionCommodityRepository.save(actionCommodity));
            //log.info("Preloading " + orderRepository.save(order));
            //log.info("Preloading " + storeActionRepository.save(release));
        };
    }
}