package com.shahid.ratingservice.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="micro_ratings")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String ratingId;
    private String userId;
    private String hotelId;
    private  int rating;
    private  String feedback;

}

// No changes needed for RatingRepository as it already extends JpaRepository