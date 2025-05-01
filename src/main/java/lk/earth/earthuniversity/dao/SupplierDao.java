package lk.earth.earthuniversity.dao;


import lk.earth.earthuniversity.entity.Item;
import lk.earth.earthuniversity.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SupplierDao extends JpaRepository<Supplier,Integer> {

    @Query("SELECT NEW Supplier (su.id, su .name) FROM Supplier su")
    List<Supplier> findAllNameId() ;


    Supplier findByCode(String code);

    @Query("select su from Supplier su where su.id = :id")
    Supplier findByMyId(@Param("id") Integer id);


    Supplier findByName(String name);
}

