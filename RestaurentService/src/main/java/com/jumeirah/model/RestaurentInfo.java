package com.jumeirah.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Data
@Document(collection = "restaurent_info")
public class RestaurentInfo {

    @Id
    private UUID restaurentInfoId = UUID.randomUUID();

    private String currencyCode;
    private String currencySymbol;
    private String restaurantName;

    @Indexed(unique = true)
    private String restaurantCode; // Enforced as unique in DB

    private String imageUrl;
    private String restaurantLocation;
    private String logoUrl;

    private Boolean inRoomDining;
    private Boolean openForGuestOrder;

    private String helpTextRoomNumber;
    private String helpTextLastName;

    private Integer deliveryTime;

    private String supportedLanguages;
    private String menuLanguage;

    private List<Disclaimer> disclaimer;

    private Boolean paymentOnlineEnabled;
    private Boolean paymentToRoomEnabled;
    private Boolean isSplitPayment;
    private Boolean onlyPayment;

    private List<ItemUnavailable> itemUnavailable;

    private String simphonyVersion;
    private String theme;

    private Boolean buzzer;
    private Boolean isPickup;

    private String irb;
    private String sports;
    private String parentOutlet;
    private String irbName;
    private Boolean isIrb;

    @Data
    public static class Disclaimer {
        private String text;
    }

    @Data
    public static class ItemUnavailable {
        private Root root;

        @Data
        public static class Root {
            private List<String> item;
        }
    }
}
