package lk.earth.earthuniversity.entity;

import lk.earth.earthuniversity.util.RegexPattern;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Arrays;

@Entity
public class Item {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    @RegexPattern(reg = "^[A-Z][a-z]+\\s[A-Z][a-z]+\\s(?:[A-Z][a-z]+\\s)?[\\d]{1,3}(kg|g|l|ml)$", msg="Invalid Name")
    private String name;
    @Basic
    @Column(name = "code")
    @RegexPattern(reg = "^[I]\\d{5}$",msg ="Invalid Code")
    private String code;
    @Basic
    @Column(name = "sprice")
    @RegexPattern(reg = "^\\d+(\\.\\d{1,2})?$", msg = "Invalid Sale Price")
    private BigDecimal sprice;

    @Basic
    @Column(name = "pprice")
    @RegexPattern(reg = "^\\d+(\\.\\d{1,2})?$", msg = "Invalid Purchase Price")
    private BigDecimal pprice;
    @Basic
    @Column(name = "photo")
    private byte[] photo;
    @Basic
    @Column(name = "quantity")
    @RegexPattern(reg = "^\\d{3}$", msg = "Invalid Quantity")
    private Integer quantity;
    @Basic
    @Column(name = "rop")
    @RegexPattern(reg = "^\\d{2}$", msg = "Invalid ROP")
    private Integer rop;
    @Basic
    @Column(name = "dointroduced")
    private Date dointroduced;
    @ManyToOne
    @JoinColumn(name = "itemstatus_id", referencedColumnName = "id", nullable = false)
    private Itemstatus itemstatus;
    @ManyToOne
    @JoinColumn(name = "unittype_id", referencedColumnName = "id", nullable = false)
    private Unittype unittype;
    @ManyToOne
    @JoinColumn(name = "subcategory_id", referencedColumnName = "id", nullable = false)
    private Subcategory subcategory;
    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "id", nullable = false)
    private Brand brand;

    public Item(){}

    public Item(Integer id, String name){
        this.id = id;
        this.name = name;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getSprice() {
        return sprice;
    }

    public void setSprice(BigDecimal sprice) {
        this.sprice = sprice;
    }

    public BigDecimal getPprice() {
        return pprice;
    }

    public void setPprice(BigDecimal pprice) {
        this.pprice = pprice;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getRop() {
        return rop;
    }

    public void setRop(Integer rop) {
        this.rop = rop;
    }

    public Date getDointroduced() {
        return dointroduced;
    }

    public void setDointroduced(Date dointroduced) {
        this.dointroduced = dointroduced;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (id != null ? !id.equals(item.id) : item.id != null) return false;
        if (name != null ? !name.equals(item.name) : item.name != null) return false;
        if (code != null ? !code.equals(item.code) : item.code != null) return false;
        if (sprice != null ? !sprice.equals(item.sprice) : item.sprice != null) return false;
        if (pprice != null ? !pprice.equals(item.pprice) : item.pprice != null) return false;
        if (!Arrays.equals(photo, item.photo)) return false;
        if (quantity != null ? !quantity.equals(item.quantity) : item.quantity != null) return false;
        if (rop != null ? !rop.equals(item.rop) : item.rop != null) return false;
        if (dointroduced != null ? !dointroduced.equals(item.dointroduced) : item.dointroduced != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (sprice != null ? sprice.hashCode() : 0);
        result = 31 * result + (pprice != null ? pprice.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(photo);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (rop != null ? rop.hashCode() : 0);
        result = 31 * result + (dointroduced != null ? dointroduced.hashCode() : 0);
        return result;
    }

    public Itemstatus getItemstatus() {
        return itemstatus;
    }

    public void setItemstatus(Itemstatus itemstatus) {
        this.itemstatus = itemstatus;
    }

    public Unittype getUnittype() {
        return unittype;
    }

    public void setUnittype(Unittype unittype) {
        this.unittype = unittype;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
