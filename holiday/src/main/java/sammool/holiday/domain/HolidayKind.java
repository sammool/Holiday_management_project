package sammool.holiday.domain;

import lombok.Getter;

public enum HolidayKind {
    BASIC("연가"),
    PRIZE("포상"),
    EFFORT("위로");

    @Getter
    private String description;

    HolidayKind(String description){
        this.description = description;
    }

    public static HolidayKind fromString(String value){
        switch (value) {
            case "BASIC":
                return BASIC;
        
            case "PRIZE":
                return PRIZE;
            case "EFFORT":
                return EFFORT;
            default:
                throw new IllegalArgumentException("Not Appropriate Value");
        }
    }
};
