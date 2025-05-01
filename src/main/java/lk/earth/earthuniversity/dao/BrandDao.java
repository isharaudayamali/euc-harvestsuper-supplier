package lk.earth.earthuniversity.dao;


import lk.earth.earthuniversity.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BrandDao extends JpaRepository<Brand,Integer> {

@Query("select b from  Brand b, Categorybrand cb where cb.brand.id= b.id and cb.id=:id")
    List<Brand>findBrandByCategory(@Param("id")Integer id);

}

