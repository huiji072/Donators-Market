package com.shop.entity;

import com.shop.constant.OfferStatus;
import com.shop.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "offers")
@Getter
@Setter
public class Offer extends BaseEntity{


    @Id
    @GeneratedValue
    @Column(name = "offer_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private LocalDateTime offerDate; //주문일

    @Enumerated(EnumType.STRING)
    private OfferStatus offerStatus; //주문상태

    private String logistics;

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL
            , orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OfferItem> offerItems = new ArrayList<>();

    public void addOfferItem(OfferItem offerItem) {
        offerItems.add(offerItem);
        offerItem.setOffer(this);
    }

    public static Offer createOffer(Member member, List<OfferItem> offerItemList ) {
        Offer offer = new Offer();
        offer.setMember(member);
//        offer.setItem(item);
//        offer.setItem(item);

        for(OfferItem offerItem : offerItemList) {
            offer.addOfferItem(offerItem);
        }

        offer.setOfferStatus(OfferStatus.Offer);
        offer.setOfferDate(LocalDateTime.now());
        return offer;
    }

    public void cancelOffer() {
        this.offerStatus = OfferStatus.CANCEL;

        for (OfferItem offerItem : offerItems) {
            offerItem.cancel();
        }
    }


}
