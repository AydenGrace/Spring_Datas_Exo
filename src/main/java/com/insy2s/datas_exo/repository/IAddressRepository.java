package com.insy2s.datas_exo.repository;

import com.insy2s.datas_exo.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAddressRepository extends JpaRepository<Address,Long> {

//    @Query(value = "select a " +
//            "from Address a " +
//            "where a.streetNumber = ?1 " +
//                "and a.streetName = ?2 " +
//                "and a.city = ?3")
    Optional<Address> findAddressByStreetNumberAndStreetNameAndCity(String streetNumber, String streetName, String city);
}
