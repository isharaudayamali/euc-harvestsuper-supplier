package lk.earth.earthuniversity.report.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lk.earth.earthuniversity.report.entity.CountByDesignation;
import lk.earth.earthuniversity.report.entity.ItemCountByCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemCountByCategoryDao extends JpaRepository<ItemCountByCategory,Integer> {

    @Query("SELECT NEW ItemCountByCategory(c.name, COUNT(*)) FROM Item i, Category c, Subcategory s WHERE i.subcategory.id = s.id and s.category.id = c.id GROUP BY c.id")
    List<ItemCountByCategory> findByItemCategoryCount();

}

