package com.itemconfiguration.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.Validate;

@Entity
@Table(name = "item_print_type")
public class ItemPrintType {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "item_print_type_id_seq")
    @SequenceGenerator(
            name = "item_print_type_id_seq",
            sequenceName = "item_print_type_id_seq",
            allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    @JoinTable(name = "print_type", joinColumns = @JoinColumn(name = "name"))
    @Column(name = "print_type_name", nullable = false)
    private PrintType printType;

    @Enumerated(EnumType.STRING)
    @Column(name = "batch_builder_type", nullable = false)
    private BatchBuilderType batchBuilderType;

    @Column(name = "is_default", nullable = false)
    private boolean defaultPrint;

    private Integer dpi;

    @OneToMany(mappedBy = "itemPrintType", cascade = CascadeType.ALL)
    @OrderBy(value = "labelType ASC")
    private List<ItemPrintTypeGlid> itemPrintTypeGlids = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    public ItemPrintType() {
    }

    public ItemPrintType(ItemPrintType other) {
        Validate.notNull(other, "[other] parametr should not be null");
        this.printType = other.printType;
        this.batchBuilderType = other.batchBuilderType;
        this.defaultPrint = other.defaultPrint;
        this.dpi = other.dpi;
        if (CollectionUtils.isNotEmpty(other.getItemPrintTypeGlids())) {
            other.getItemPrintTypeGlids()
                    .stream()
                    .map(ItemPrintTypeGlid::new)
                    .forEach(this::addItemPrintTypeGlid);
        }
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PrintType getPrintType() {
        return printType;
    }

    public void setPrintType(PrintType printType) {
        this.printType = printType;
    }

    public BatchBuilderType getBatchBuilderType() {
        return batchBuilderType;
    }

    public void setBatchBuilderType(BatchBuilderType batchBuilderType) {
        this.batchBuilderType = batchBuilderType;
    }

    public boolean isDefaultPrint() {
        return defaultPrint;
    }

    public void setDefaultPrint(boolean defaultPrint) {
        this.defaultPrint = defaultPrint;
    }

    public void addItemPrintTypeGlid(ItemPrintTypeGlid itemPrintTypeGlid) {
        itemPrintTypeGlid.setItemPrintType(this);
        this.itemPrintTypeGlids.add(itemPrintTypeGlid);
    }

    public Integer getDpi() {
        return dpi;
    }

    public void setDpi(Integer dpi) {
        this.dpi = dpi;
    }

    public List<ItemPrintTypeGlid> getItemPrintTypeGlids() {
        return itemPrintTypeGlids;
    }

    public void setItemPrintTypeGlids(List<ItemPrintTypeGlid> itemPrintTypeGlids) {
        this.itemPrintTypeGlids = itemPrintTypeGlids;
    }
}
