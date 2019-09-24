package com.itemconfiguration.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.apache.commons.lang3.Validate;

@Entity
@Table(name = "item_print_type_glid")
public class ItemPrintTypeGlid {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "item_print_type_glid_id_seq"
    )
    @SequenceGenerator(
            name = "item_print_type_glid_id_seq",
            sequenceName = "item_print_type_glid_id_seq",
            allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    private LabelType labelType;

    @Column(name = "is_parent", nullable = false)
    private boolean parent;

    @Column(name = "[order]")
    private Integer order;

    private String glid;

    @ManyToOne(
            fetch = FetchType.LAZY,
            targetEntity = ItemPrintType.class
    )
    private ItemPrintType itemPrintType;

    public ItemPrintTypeGlid() {
    }

    public ItemPrintTypeGlid(ItemPrintTypeGlid other) {
        Validate.notNull(other, "[other] parametr should not be null");
        this.labelType = other.labelType;
        this.parent = other.parent;
        this.order = other.order;
        this.glid = other.glid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LabelType getLabelType() {
        return labelType;
    }

    public void setLabelType(LabelType labelType) {
        this.labelType = labelType;
    }

    public boolean isParent() {
        return parent;
    }

    public void setParent(boolean parent) {
        this.parent = parent;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getGlid() {
        return glid;
    }

    public void setGlid(String glid) {
        this.glid = glid;
    }

    public ItemPrintType getItemPrintType() {
        return itemPrintType;
    }

    public void setItemPrintType(ItemPrintType itemPrintType) {
        this.itemPrintType = itemPrintType;
    }
}
