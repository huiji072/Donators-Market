package com.shop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//@Table(name = "offer_item")
@Entity
@Getter
@Setter
public class OfferItem extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "offer_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offer_id")
    private Offer offer;
    

    private int count; //수량

//    private String itemSeller;

    public static OfferItem createOfferItem(Item item, int count){
        OfferItem offerItem = new OfferItem();
        offerItem.setItem(item);
        offerItem.setCount(count);
        item.removeStock(count);
        return offerItem;
    }

    public void cancel() {
        this.getItem().addStock(count);
    }


}
