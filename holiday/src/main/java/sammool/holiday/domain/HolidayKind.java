package sammool.holiday.domain;

public enum HolidayKind {
    BASIC,
    PRIZE,
    EFFORT;

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
