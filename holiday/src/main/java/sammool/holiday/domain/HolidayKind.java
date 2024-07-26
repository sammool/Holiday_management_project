package sammool.holiday.domain;

public enum HolidayKind {
    BASIC,
    PRIZE,
    EFFORT;

    public static HolidayKind fromString(String value){
        switch (value) {
            case "연가":
                return BASIC;
        
            case "포상":
                return PRIZE;
            case "위로":
                return EFFORT;
            default:
                throw new IllegalArgumentException("Not Appropriate Value");
        }
    }
};
