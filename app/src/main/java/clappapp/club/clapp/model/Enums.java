package clappapp.club.clapp.model;

public class Enums {
    public enum Privacy {
        GLOBAL,
        LOCAL,
        CLUB,
        GROUP,
        PRIVATE;

        @Override
        public String toString() {
            return name().charAt(0) + name().substring(1).toLowerCase();
        }
    }

    public enum EventType {
        CONFERENCE,
        MEETING,
        COMPETITION,
        PARTY,
        WORKSHOP,
        TRIP,
        NIGHTOUT,
        TRAINING;

        @Override
        public String toString() {
            return name().charAt(0) + name().substring(1).toLowerCase();
        }
    }

    public enum Gender {
        MALE,
        FEMALE,
        OTHER
    }
}
