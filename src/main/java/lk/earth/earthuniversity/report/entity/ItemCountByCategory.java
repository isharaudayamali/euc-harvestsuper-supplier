package lk.earth.earthuniversity.report.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import sun.jvm.hotspot.types.CIntegerField;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ItemCountByCategory {
@JsonIgnore
    @Id
    private Integer id;
    private String name;
    private Long count;

    public ItemCountByCategory(String name, Long count) {
        this.name = name;
        this.count = count;
    }

    public ItemCountByCategory() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
