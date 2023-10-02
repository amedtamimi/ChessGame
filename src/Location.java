import java.util.Objects;

public class Location {
    private final int file;
    private final int rank;

    public Location(Integer file, Integer rank) {
        this.file = file;
        this.rank = rank;
    }

    public Location(String location) {
        this(56-location.charAt(1) , location.charAt(0)-65 );
    }

    public int getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(file, location.file) && Objects.equals(rank, location.rank);
    }

    @Override
    public String toString() {
        return "Location{" +
                "file=" + file +
                ", rank=" + rank +
                '}';
    }
}
