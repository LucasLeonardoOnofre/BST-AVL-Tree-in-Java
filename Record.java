public class Record{
    //Fields
    private String genre;
    private int year;
    private String artist;
    private String title;

    // Constructor
public Record(String genre, int year, String artist, String title) {
    this.genre = genre;
    this.year = year;
    this.artist = artist;
    this.title = title;
}
// Getters and Setters
public String getGenre() {
    return genre;
}
public int getYear() {
    return year;
}
public String getArtist() {
    return artist;
}
public String getTitle() {
    return title;
}
// toString method
@Override
public String toString() {
     return genre + " | " + year + " | " + artist + " | " + title;
        }

//Method to compare records by genre, then year, then artist, then title
public int compareTo(Record other) {
   int genreCmp = this.genre.compareTo(other.genre);
   if (genreCmp != 0) {
       return genreCmp;
   }
    int yearCmp = Integer.compare(this.year, other.year);
    if (yearCmp != 0) {
        return yearCmp;
    }
    // If genres and years are equal, compare by artist
    int artistCmp = this.artist.compareTo(other.artist);
    if (artistCmp != 0) {
        return artistCmp;
    }
    // If all fields are equal, compare by title
    return this.title.compareTo(other.title);

}
}